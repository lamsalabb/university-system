package com.university.core.mvccontroller.instructor;

import com.university.common.entity.Course;
import com.university.common.entity.User;
import com.university.core.service.EnrollmentService;
import com.university.core.service.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/instructor")
public class InstructorCoursesController {

    private final EnrollmentService enrollmentService;
    private final UserService userService;

    public InstructorCoursesController(EnrollmentService enrollmentService,
                                       UserService userService) {
        this.enrollmentService = enrollmentService;
        this.userService = userService;
    }

    @GetMapping("/courses")
    public String myCourses(Authentication authentication, Model model) {

        // ✅ Spring Security user (NOT entity)
        String email = authentication.getName();

        // ✅ Load your actual User entity
        User instructor = userService.findUserByEmail(email);

        List<Course> courses =
                enrollmentService.getCoursesByInstructor(instructor.getId());

        model.addAttribute("courses", courses);

        return "instructor/courses";
    }
}
