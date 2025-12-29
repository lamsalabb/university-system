package com.university.core.mvccontroller.admin;

import com.university.common.entity.Fee;
import com.university.core.service.FeeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/admin/fees")
public class AdminFeeController {

    private final FeeService feeService;

    public AdminFeeController(FeeService feeService) {
        this.feeService = feeService;
    }

    @GetMapping
    public String viewFees(Model model) {
        List<Fee> fees = feeService.getAllFees();
        model.addAttribute("fees", fees);
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
}
