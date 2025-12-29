package com.university.core.mvccontroller.student;

import com.university.common.entity.User;
import com.university.core.service.EnrollmentService;
import com.university.core.service.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/student")
public class StudentCoursePageController {

    private final EnrollmentService enrollmentService;
    private final UserService userService;

    public StudentCoursePageController(EnrollmentService enrollmentService,
                                       UserService userService) {
        this.enrollmentService = enrollmentService;
        this.userService = userService;
    }

    @GetMapping("/courses")
    public String courses(Authentication auth, Model model) {

        String email = auth.getName();
        User student = userService.findUserByEmail(email);

        model.addAttribute(
                "enrollments",
                enrollmentService.getEnrollmentsByStudent(student.getId())
        );

        return "student/courses";
    }
}
