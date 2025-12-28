package com.university.core.mvccontroller.instructor;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/instructor")
public class InstructorDashboardController {

    @GetMapping("/dashboard")
    public String dashboard() {
        return "instructor/dashboard";
    }
}
