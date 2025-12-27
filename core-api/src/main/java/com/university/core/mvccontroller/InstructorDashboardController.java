package com.university.core.mvccontroller;

import com.university.common.entity.User;
import com.university.core.service.CourseService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Objects;

@Controller
@RequestMapping("/instructor")
public class InstructorDashboardController {

    private final CourseService courseService;

    public InstructorDashboardController(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping("/dashboard")
    public String dashboard(Model model, Authentication auth) {

        User instructor = (User) auth.getPrincipal();

        model.addAttribute("courses",
                courseService.getAllCourses().stream()
                        .filter(c -> Objects.equals(c.getInstructor().getId(), instructor.getId()))
                        .toList());

        return "instructor/dashboard";
    }
}
