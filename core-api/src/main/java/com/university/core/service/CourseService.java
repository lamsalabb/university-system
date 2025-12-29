package com.university.core.service;

import com.university.common.entity.Course;
import com.university.common.entity.User;
import com.university.common.repository.CourseRepository;
import com.university.common.repository.UserRepository;
import com.university.core.exception.CourseAlreadyExistsException;
import com.university.core.exception.CourseNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseService {

    private final CourseRepository courseRepository;
    private final UserRepository userRepository;

    public CourseService(CourseRepository courseRepository, UserRepository userRepository) {
        this.courseRepository = courseRepository;
        this.userRepository = userRepository;
    }



    @Transactional//CREATE
    public Course createCourse(Course course){
        if(courseRepository.findByCode(course.getCode()).isPresent()){
            throw new CourseAlreadyExistsException("Course with code "+course.getCode()+" already exists.");
        }
        return courseRepository.save(course);
    }



    //READ
    public List<Course> getAllCourses(){
        return courseRepository.findAll();
    }

    public Course findCourseById(int id){
        return courseRepository.findById(id).orElseThrow(() ->new CourseNotFoundException("Course not found with id: "+id));
    }


    @Transactional//UPDATE
    public Course updateCourse(int id, Course updatedCourseDetails){
        Course existingCourse = findCourseById(id);

        existingCourse.setTitle(updatedCourseDetails.getTitle());
        existingCourse.setCode(updatedCourseDetails.getCode());
        existingCourse.setCredits(updatedCourseDetails.getCredits());
        existingCourse.setDescription(updatedCourseDetails.getDescription());
        existingCourse.setInstructor(updatedCourseDetails.getInstructor());

        return courseRepository.save(existingCourse);
    }



    @Transactional//DELETE
    public void deleteCourse(int id){
        if (!courseRepository.existsById(id)){
            throw new CourseNotFoundException("Cannot delete. Course not found with id: " + id);
        }
        courseRepository.deleteById(id);
    }


    public List<Course> getCoursesByInstructorId(int instructorId) {
        return courseRepository.findByInstructorId(instructorId);
    }
    @Transactional
    public void assignInstructor(int courseId, int instructorId) {

        Course course = courseRepository.findById(courseId)
                .orElseThrow(() ->
                        new RuntimeException("Course not found with id: " + courseId));

        User instructor = userRepository.findById(instructorId)
                .orElseThrow(() ->
                        new RuntimeException("Instructor not found with id: " + instructorId));

        if (instructor.getRole() != User.Role.INSTRUCTOR) {
            throw new RuntimeException("Selected user is not an instructor");
        }

        course.setInstructor(instructor);
        courseRepository.save(course);
    }



}
