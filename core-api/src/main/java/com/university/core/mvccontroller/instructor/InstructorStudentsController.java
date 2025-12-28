package com.university.core.mvccontroller.instructor;

import com.university.common.entity.Enrollment;
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
public class InstructorStudentsController {

    private final EnrollmentService enrollmentService;
    private final UserService userService;

    public InstructorStudentsController(EnrollmentService enrollmentService,
                                        UserService userService) {
        this.enrollmentService = enrollmentService;
        this.userService = userService;
    }

    @GetMapping("/students")
    public String students(Authentication auth, Model model) {

        String email = auth.getName();
        User instructor = userService.findUserByEmail(email);

        List<Enrollment> enrollments =
                enrollmentService.getEnrollmentsByInstructor(instructor.getId());

        model.addAttribute("enrollments", enrollments);

        return "instructor/students";
    }
}
