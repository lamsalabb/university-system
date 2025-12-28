package com.university.core.mvccontroller.admin;

import com.university.common.entity.Course;
import com.university.core.service.CourseService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin/courses")
public class AdminCoursePageController {

    private final CourseService courseService;

    public AdminCoursePageController(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping
    public String list(Model model) {
        model.addAttribute("courses", courseService.getAllCourses());
        return "admin/courses";
    }

    @PostMapping
    public String create(@ModelAttribute Course course) {
        courseService.createCourse(course);
        return "redirect:/admin/courses";
    }

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable int id) {
        courseService.deleteCourse(id);
        return "redirect:/admin/courses";
    }
}
