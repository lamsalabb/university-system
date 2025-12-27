package com.university.core.mvccontroller;

import com.university.core.service.CourseService;
import com.university.core.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminDashboardController {

    private final UserService userService;
    private final CourseService courseService;

    public AdminDashboardController(UserService userService, CourseService courseService) {
        this.userService = userService;
        this.courseService = courseService;
    }

    @GetMapping("/dashboard")
    public String dashboard(Model model) {

        model.addAttribute("users", userService.findAllUsers().size());
        model.addAttribute("courses", courseService.getAllCourses().size());

        return "admin/dashboard";
    }
}
