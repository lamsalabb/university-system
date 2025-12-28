package com.university.core.mvccontroller.student;

import com.university.common.entity.User;
import com.university.core.service.FeeService;
import com.university.core.service.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/student")
public class StudentFeePageController {

    private final FeeService feeService;
    private final UserService userService;

    public StudentFeePageController(FeeService feeService,
                                    UserService userService) {
        this.feeService = feeService;
        this.userService = userService;
    }

    @GetMapping("/fees")
    public String fees(Authentication auth, Model model) {

        String email = auth.getName();
        User student = userService.findUserByEmail(email);

        model.addAttribute(
                "fees",
                feeService.getFeesByStudent(student.getId())
        );

        model.addAttribute(
                "outstanding",
                feeService.calculateOutstandingFee(student.getId())
        );

        return "student/fees";
    }
}
