package com.university.core.mvccontroller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/analytics")
public class AdminAnalyticsController {

    @GetMapping
    public String analytics() {
        return "admin/analytics";
    }
}
