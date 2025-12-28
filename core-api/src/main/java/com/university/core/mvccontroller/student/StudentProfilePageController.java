package com.university.core.mvccontroller.student;

import com.university.common.entity.User;
import com.university.core.service.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/student")
public class StudentProfilePageController {

    private final UserService userService;

    public StudentProfilePageController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/profile")
    public String profile(Authentication auth, Model model) {

        String email = auth.getName();
        User student = userService.findUserByEmail(email);

        model.addAttribute("student", student);
        return "student/profile";
    }
}
