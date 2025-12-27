package com.university.core.mvccontroller;

import com.university.common.entity.User;
import com.university.core.service.FeeService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/student/fees")
public class StudentFeePageController {

    private final FeeService feeService;

    public StudentFeePageController(FeeService feeService) {
        this.feeService = feeService;
    }

    @GetMapping
    public String myFees(Authentication auth, Model model) {

        int studentId = ((User) auth.getPrincipal()).getId();

        model.addAttribute("fees",
                feeService.getFeesByStudent(studentId));

        model.addAttribute("outstanding",
                feeService.calculateOutstandingFee(studentId));

        return "student/fees";
    }
}
