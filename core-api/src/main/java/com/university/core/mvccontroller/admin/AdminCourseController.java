package com.university.core.mvccontroller.admin;

import com.university.common.entity.Course;
import com.university.common.entity.User;
import com.university.core.service.CourseService;
import com.university.core.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin/courses")
public class AdminCourseController {

    private final CourseService courseService;
    private final UserService userService;

    public AdminCourseController(CourseService courseService,
                                 UserService userService) {
        this.courseService = courseService;
        this.userService = userService;
    }

    @GetMapping
    public String courses(Model model) {

        List<Course> courses = courseService.getAllCourses();
        List<User> instructors = userService.getUsersByRole(User.Role.INSTRUCTOR);

        model.addAttribute("courses", courses);
        model.addAttribute("instructors", instructors);
        model.addAttribute("newCourse", new Course());

        return "admin/courses";
    }

    @PostMapping
    public String createCourse(@ModelAttribute Course course,
                               @RequestParam int instructorId) {

        User instructor = userService.findUserById(instructorId);
        course.setInstructor(instructor);

        courseService.createCourse(course);
        return "redirect:/admin/courses";
    }

    @PostMapping("/update")
    public String updateCourse(@ModelAttribute Course course,
                               @RequestParam int instructorId) {

        User instructor = userService.findUserById(instructorId);
        course.setInstructor(instructor);

        courseService.updateCourse(course.getId(), course);
        return "redirect:/admin/courses";
    }

    @PostMapping("/delete")
    public String deleteCourse(@RequestParam int courseId) {
        courseService.deleteCourse(courseId);
        return "redirect:/admin/courses";
    }
}
