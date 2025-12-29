package com.university.core.mvccontroller.student;

import com.university.common.entity.Course;
import com.university.common.entity.Enrollment;
import com.university.common.entity.User;
import com.university.core.service.CourseService;
import com.university.core.service.EnrollmentService;
import com.university.core.service.FeeService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/student/enrollments")
public class StudentEnrollmentPageController {

    private final EnrollmentService enrollmentService;
    private final CourseService courseService;
    private final FeeService feeService;

    public StudentEnrollmentPageController(EnrollmentService enrollmentService,
                                           CourseService courseService,
                                           FeeService feeService) {
        this.enrollmentService = enrollmentService;
        this.courseService = courseService;
        this.feeService = feeService;
    }

    @GetMapping
    public String enrollments(Authentication auth, Model model) {

        int studentId = ((User) auth.getPrincipal()).getId();

        model.addAttribute("enrollments",
                enrollmentService.getEnrollmentsByStudent(studentId));

        int outstanding = feeService.calculateOutstandingFee(studentId);
        model.addAttribute("outstanding", outstanding);
        model.addAttribute("blocked", outstanding > 4000);

        return "student/enrollments";
    }

    @GetMapping("/available")
    public String availableCourses(Model model) {
        model.addAttribute("courses", courseService.getAllCourses());
        return "student/available-courses";
    }

    @PostMapping("/enroll")
    public String enroll(@RequestParam int courseId,
                         @RequestParam String semester,
                         Authentication auth) {

        int studentId = ((User) auth.getPrincipal()).getId();

        Enrollment enrollment = new Enrollment();
        enrollment.setStudent(User.builder().id(studentId).build());
        enrollment.setCourse(Course.builder().id(courseId).build());
        enrollment.setSemester(semester);

        enrollmentService.enroll(enrollment);

        return "redirect:/student/enrollments";
    }

    @PostMapping("/drop/{enrollmentId}")
    public String drop(@PathVariable int enrollmentId) {
        enrollmentService.dropEnrollment(enrollmentId);
        return "redirect:/student/enrollments";
    }
}
