package com.university.core.mvccontroller.instructor;

import com.university.common.repository.UserRepository;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class InstructorProfileController {

    private final UserRepository userRepository;

    public InstructorProfileController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/instructor/profile")
    public String profile(Authentication auth, Model model) {

        model.addAttribute("user",
                userRepository.findByEmail(auth.getName()).orElseThrow());

        return "instructor/profile";
    }
}
