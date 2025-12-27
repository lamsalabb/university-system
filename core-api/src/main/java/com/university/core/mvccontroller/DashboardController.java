package com.university.core.mvccontroller;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DashboardController {

    @GetMapping("/dashboard")
    public String dashboard(Authentication auth) {

        if (auth == null) {
            return "redirect:/login";
        }

        var authorities = auth.getAuthorities();

        if (authorities.stream().anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"))) {
            return "redirect:/admin/dashboard";
        }

        if (authorities.stream().anyMatch(a -> a.getAuthority().equals("ROLE_INSTRUCTOR"))) {
            return "redirect:/instructor/dashboard";
        }

        if (authorities.stream().anyMatch(a -> a.getAuthority().equals("ROLE_STUDENT"))) {
            return "redirect:/student/dashboard";
        }

        return "redirect:/login";
    }
}
