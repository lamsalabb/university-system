package com.university.core.mvccontroller;

import com.university.common.entity.User;
import com.university.core.service.EmailService;
import com.university.core.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class PublicRegistrationPageController {

    private final UserService userService;
    private final EmailService emailService;

    public PublicRegistrationPageController(UserService userService,
                                            EmailService emailService) {
        this.userService = userService;
        this.emailService = emailService;
    }

    @GetMapping("/register")
    public String showRegister(Model model) {
        model.addAttribute("user", new User());
        return "auth/register";
    }

    @PostMapping("/register")
    public String handleRegister(@ModelAttribute User user,
                                 @RequestParam(required = false) String otp,
                                 Model model) {


        if (otp != null && !otp.isBlank()) {

            boolean valid = emailService.validateOTP(user.getEmail(), otp);

            if (!valid) {
                model.addAttribute("otpSent", true);
                model.addAttribute("otpError", "Invalid OTP");
                return "auth/register";
            }

            user.setRole(User.Role.STUDENT);
            userService.registerNewUser(user);

            return "redirect:/login?registered=true";
        }

        emailService.generateAndSendOTP(user.getEmail());
        model.addAttribute("otpSent", true);

        return "auth/register";
    }
}
