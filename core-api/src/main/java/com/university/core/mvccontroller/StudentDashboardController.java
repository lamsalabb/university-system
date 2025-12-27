package com.university.core.mvccontroller;

import com.university.common.entity.User;
import com.university.core.service.EnrollmentService;
import com.university.core.service.FeeService;
import com.university.core.service.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/student")
public class StudentDashboardController {

    private final EnrollmentService enrollmentService;
    private final FeeService feeService;
    private final UserService userService;

    public StudentDashboardController(EnrollmentService enrollmentService,
                                      FeeService feeService,
                                      UserService userService) {
        this.enrollmentService = enrollmentService;
        this.feeService = feeService;
        this.userService = userService;
    }

    @GetMapping("/dashboard")
    public String dashboard(Authentication auth, Model model) {

        // ✅ username = email
        String email = auth.getName();

        // ✅ load your entity explicitly
        User student = userService.findUserByEmail(email);

        model.addAttribute("student", student);

        model.addAttribute("enrollments",
                enrollmentService.getEnrollmentByStudent(student.getId()));

        model.addAttribute("outstanding",
                feeService.calculateOutstandingFee(student.getId()));

        return "student/dashboard";
    }
}
