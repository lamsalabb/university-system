package com.university.core.mvccontroller;

import com.university.core.client.ReportingClient;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/reports")
public class AdminReportPageController {

    private final ReportingClient reportingClient;

    public AdminReportPageController(ReportingClient reportingClient) {
        this.reportingClient = reportingClient;
    }

    @GetMapping
    public String reports(Model model) {

        model.addAttribute("activeStudents",
                reportingClient.getActiveStudents());

        model.addAttribute("averageGrades",
                reportingClient.getAverageGrades());

        model.addAttribute("courseEnrollments",
                reportingClient.getCourseEnrollmentStats());

        return "admin/reports";
    }
}
