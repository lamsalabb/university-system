package com.university.core.mvccontroller;

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
public class StudentGradesPageController {

    private final EnrollmentService enrollmentService;
    private final UserService userService;

    public StudentGradesPageController(EnrollmentService enrollmentService,
                                   UserService userService) {
        this.enrollmentService = enrollmentService;
        this.userService = userService;
    }

    @GetMapping("/grades")
    public String grades(Authentication auth, Model model) {

        String email = auth.getName(); // ✔ Spring Security user
        User student = userService.findUserByEmail(email); // ✔ JPA user

        model.addAttribute(
                "enrollments",
                enrollmentService.getEnrollmentByStudent(student.getId())
        );

        return "student/grades";
    }
}
