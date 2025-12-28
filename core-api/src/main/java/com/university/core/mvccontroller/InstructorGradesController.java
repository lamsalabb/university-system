package com.university.core.mvccontroller;

import com.university.core.service.EnrollmentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/instructor")
public class InstructorGradesController {

    private final EnrollmentService enrollmentService;

    public InstructorGradesController(EnrollmentService enrollmentService) {
        this.enrollmentService = enrollmentService;
    }

    @GetMapping("/grades/{courseId}")
    public String gradePage(@PathVariable int courseId, Model model) {

        model.addAttribute(
                "enrollments",
                enrollmentService.getEnrollmentByCourse(courseId)
        );

        return "instructor/grades";
    }

    @PostMapping("/grades/{enrollmentId}")
    public String submitGrade(@PathVariable int enrollmentId,
                              @RequestParam String grade) {

        enrollmentService.assignGrade(enrollmentId, grade);

        return "redirect:/instructor/dashboard";
    }
}
