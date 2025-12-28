package com.university.core.mvccontroller.admin;

import com.university.common.entity.User;
import com.university.core.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/users")
public class AdminUserPageController {

    private final UserService userService;

    public AdminUserPageController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public String create(@ModelAttribute User user) {
        userService.registerNewUser(user);
        return "redirect:/admin/users";
    }
}
