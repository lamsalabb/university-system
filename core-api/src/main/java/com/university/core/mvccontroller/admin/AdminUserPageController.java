package com.university.core.mvccontroller.admin;

import com.university.common.entity.User;
import com.university.core.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin/users")
public class AdminUserPageController {

    private final UserService userService;

    public AdminUserPageController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String viewUsers(Model model) {

        List<User> users = userService.findAllUsers();

        model.addAttribute("users", users);
        model.addAttribute("newUser", new User());

        return "admin/users";
    }


    @PostMapping
    public String createUser(@ModelAttribute("newUser") User user) {
        userService.registerNewUser(user);
        return "redirect:/admin/users";
    }


    @PostMapping("/update")
    public String updateUser(@ModelAttribute User user) {

        userService.updateUser(user.getId(),user);
        return "redirect:/admin/users";
    }


    @PostMapping("/toggle")
    public String toggleUser(@RequestParam Integer userId) {

        userService.toggleActive(userId);
        return "redirect:/admin/users";
    }
}
