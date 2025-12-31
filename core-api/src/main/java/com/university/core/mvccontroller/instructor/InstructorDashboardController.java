package com.university.core.mvccontroller.instructor;

import com.university.common.entity.User;
import com.university.core.service.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class InstructorDashboardController {

    private final UserService userService;

    public InstructorDashboardController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/instructor/dashboard")
    public String instructorDashboard(Authentication authentication, Model model) {

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        User instructor = userService.findUserByEmail(userDetails.getUsername());

        model.addAttribute("instructor", instructor);

        return "instructor/dashboard";
    }
}
