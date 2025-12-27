package com.university.core.mvccontroller;

import com.university.common.entity.User;
import com.university.core.service.FeeService;
import com.university.core.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin/fees")
public class AdminFeePageController {

    private final FeeService feeService;
    private final UserService userService;

    public AdminFeePageController(FeeService feeService,
                                  UserService userService) {
        this.feeService = feeService;
        this.userService = userService;
    }

    /**
     * Step 1: show all students (admin selects one)
     */
    @GetMapping
    public String selectStudent(Model model) {
        model.addAttribute("students",
                userService.findAllByRole(User.Role.STUDENT));
        return "admin/fees-select-student";
    }

    /**
     * Step 2: show fees for selected student
     */
    @GetMapping("/student/{studentId}")
    public String feesByStudent(@PathVariable int studentId,
                                Model model) {

        model.addAttribute("fees",
                feeService.getFeesByStudent(studentId));

        model.addAttribute("outstanding",
                feeService.calculateOutstandingFee(studentId));

        model.addAttribute("studentId", studentId);

        return "admin/fees";
    }

    @PostMapping("/{feeId}/pay")
    public String pay(@PathVariable int feeId,
                      @RequestParam int studentId) {

        feeService.markFeePaid(feeId);
        return "redirect:/admin/fees/student/" + studentId;
    }

    @PostMapping("/{feeId}/unpay")
    public String unpay(@PathVariable int feeId,
                        @RequestParam int studentId) {

        feeService.markFeeUnpaid(feeId);
        return "redirect:/admin/fees/student/" + studentId;
    }
}
