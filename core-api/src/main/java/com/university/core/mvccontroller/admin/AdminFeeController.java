package com.university.core.mvccontroller.admin;

import com.university.common.entity.Fee;
import com.university.common.entity.User;
import com.university.core.service.FeeService;
import com.university.core.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/admin/fees")
public class AdminFeeController {

    private final FeeService feeService;
    private final UserService userService;

    public AdminFeeController(FeeService feeService, UserService userService) {
        this.feeService = feeService;
        this.userService = userService;
    }

    @GetMapping
    public String viewFees(Model model) {
        List<Fee> fees = feeService.getAllFees();
        List<User> students = userService.findAllByRole(User.Role.STUDENT);

        model.addAttribute("fees", fees);
        model.addAttribute("students", students);
        model.addAttribute("newFee", new Fee());

        return "admin/fees";
    }

    @PostMapping("/toggle")
    public String toggleFeeStatus(@RequestParam int feeId) {

        Fee fee = feeService.getFeeById(feeId);

        if (fee.isPaid()) {
            fee.setPaid(false);
            fee.setPaymentDate(null);
        } else {
            fee.setPaid(true);
            fee.setPaymentDate(LocalDate.now());
        }

        feeService.save(fee);
        return "redirect:/admin/fees";
    }

    @PostMapping("/create")
    public String createFee(@RequestParam int studentId,
                            @RequestParam int amount,
                            @RequestParam Fee.Type type,
                            @RequestParam LocalDate dueDate) {

        Fee fee = Fee.builder()
                .student(userService.findUserById(studentId))
                .amount(amount)
                .type(type)
                .dueDate(dueDate)
                .isPaid(false)
                .build();

        feeService.save(fee);
        return "redirect:/admin/fees";
    }
}
