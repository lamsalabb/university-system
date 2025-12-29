package com.university.core.service;

import com.university.common.entity.Course;
import com.university.common.entity.Enrollment;
import com.university.common.entity.User;
import com.university.common.repository.CourseRepository;
import com.university.common.repository.EnrollmentRepository;
import com.university.common.repository.UserRepository;
import com.university.core.exception.*;
import com.university.fee.exception.OutstandingFeesException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class EnrollmentService {

    private final EnrollmentRepository enrollmentRepository;
    private final UserRepository userRepository;
    private final CourseRepository courseRepository;
    private final FeeService feeService;

    public EnrollmentService(EnrollmentRepository enrollmentRepository,
                             UserRepository userRepository,
                             CourseRepository courseRepository,
                             FeeService feeService) {
        this.enrollmentRepository = enrollmentRepository;
        this.userRepository = userRepository;
        this.courseRepository = courseRepository;
        this.feeService = feeService;
    }

    @Transactional
    public Enrollment enroll(Enrollment request) {

        User student = userRepository.findById(request.getStudent().getId())
                .orElseThrow(() -> new UserNotFoundException(
                        "Student not found with id: " + request.getStudent().getId()));

        if (student.getRole() != User.Role.STUDENT) {
            throw new NonStudentEnrollmentException("Only students can be enrolled");
        }

        Course course = courseRepository.findById(request.getCourse().getId())
                .orElseThrow(() -> new CourseNotFoundException(
                        "Course not found with id: " + request.getCourse().getId()));

        enrollmentRepository
                .findByStudentAndCourseAndSemester(student, course, request.getSemester())
                .ifPresent(e -> {
                    throw new EnrollmentAlreadyExistsException(
                            "Student already enrolled for this semester");
                });

        int threshold = 4000;
        if (feeService.hasOutstandingFeesAboveThreshold(student.getId(), threshold)) {
            throw new OutstandingFeesException(
                    "Enrollment blocked due to outstanding fees");
        }

        request.setStudent(student);
        request.setCourse(course);
        request.setStatus(Enrollment.Status.ENROLLED);
        request.setEnrollmentDate(LocalDate.now());

        return enrollmentRepository.save(request);
    }

    @Transactional
    public void dropEnrollment(int enrollmentId) {
        Enrollment enrollment = getEnrollmentById(enrollmentId);
        enrollment.setStatus(Enrollment.Status.DROPPED);
        enrollmentRepository.save(enrollment);
    }

    public Enrollment getEnrollmentById(int id) {
        return enrollmentRepository.findById(id)
                .orElseThrow(() ->
                        new EnrollmentNotFoundException(
                                "Enrollment not found with id: " + id));
    }

    public List<Enrollment> getEnrollmentsByStudent(int studentId) {
        return enrollmentRepository.findByStudentId(studentId);
    }

    public List<Enrollment> getEnrollmentsByCourse(int courseId) {
        return enrollmentRepository.findByCourseId(courseId);
    }

    public List<Enrollment> getEnrollmentsByInstructor(int instructorId) {
        return enrollmentRepository.findByCourseInstructorId(instructorId);
    }

    public List<Course> getCoursesByInstructor(int instructorId) {
        return getEnrollmentsByInstructor(instructorId)
                .stream()
                .map(Enrollment::getCourse)
                .distinct()
                .toList();
    }

    public List<Enrollment> getActiveEnrollmentsByCourse(int courseId) {
        return enrollmentRepository.findByCourseId(courseId)
                .stream()
                .filter(e -> e.getStatus() == Enrollment.Status.ENROLLED)
                .toList();
    }

    @Transactional
    public Enrollment assignGrade(int enrollmentId, String grade) {
        Enrollment enrollment = getEnrollmentById(enrollmentId);
        enrollment.setGrade(grade);
        enrollment.setStatus(Enrollment.Status.COMPLETED);
        return enrollmentRepository.save(enrollment);
    }

    public List<Enrollment> getAllEnrollments() {
        return enrollmentRepository.findAll();
    }

}
