package com.university.core.mvccontroller.admin;

import com.university.common.entity.Enrollment;
import com.university.common.entity.User;
import com.university.core.service.CourseService;
import com.university.core.service.EnrollmentService;
import com.university.core.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin/enrollments")
public class AdminEnrollmentController {

    private final EnrollmentService enrollmentService;
    private final UserService userService;
    private final CourseService courseService;

    public AdminEnrollmentController(
            EnrollmentService enrollmentService,
            UserService userService,
            CourseService courseService
    ) {
        this.enrollmentService = enrollmentService;
        this.userService = userService;
        this.courseService = courseService;
    }

    @GetMapping
    public String page(
            @RequestParam(required = false) Integer studentId,
            @RequestParam(required = false) Integer courseId,
            @RequestParam(required = false) String semester,
            @RequestParam(required = false) Enrollment.Status status,
            Model model
    ) {

        List<Enrollment> enrollments = enrollmentService.getAllEnrollments();

        if (studentId != null) {
            enrollments = enrollments.stream()
                    .filter(e -> e.getStudent().getId().equals(studentId))
                    .toList();
        }

        if (courseId != null) {
            enrollments = enrollments.stream()
                    .filter(e -> e.getCourse().getId().equals(courseId))
                    .toList();
        }

        if (semester != null && !semester.isBlank()) {
            enrollments = enrollments.stream()
                    .filter(e -> semester.equalsIgnoreCase(e.getSemester()))
                    .toList();
        }

        if (status != null) {
            enrollments = enrollments.stream()
                    .filter(e -> e.getStatus() == status)
                    .toList();
        }

        model.addAttribute("enrollments", enrollments);
        model.addAttribute("students", userService.getUsersByRole(User.Role.STUDENT));
        model.addAttribute("courses", courseService.getAllCourses());
        model.addAttribute("enrollment", new Enrollment());

        return "admin/enrollments";
    }

    @PostMapping
    public String enroll(@ModelAttribute Enrollment enrollment) {
        enrollmentService.enroll(enrollment);
        return "redirect:/admin/enrollments";
    }

    @PostMapping("/{id}/drop")
    public String drop(@PathVariable int id) {
        enrollmentService.dropEnrollment(id);
        return "redirect:/admin/enrollments";
    }
}
