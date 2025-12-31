package com.university.core.mvccontroller.instructor;

import com.university.common.entity.Enrollment;
import com.university.common.entity.User;
import com.university.core.service.EnrollmentService;
import com.university.core.service.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/instructor")
public class InstructorGradesController {

    private final EnrollmentService enrollmentService;
    private final UserService userService;

    public InstructorGradesController(EnrollmentService enrollmentService,
                                      UserService userService) {
        this.enrollmentService = enrollmentService;
        this.userService = userService;
    }


    @GetMapping("/grades")
    public String viewGrades(Authentication authentication, Model model) {

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String email = userDetails.getUsername();

        User instructor = userService.findUserByEmail(email);

        List<Enrollment> enrollments =
                enrollmentService.getEnrollmentsByInstructor(instructor.getId());

        model.addAttribute("enrollments", enrollments);

        model.addAttribute("grades",
                List.of("A", "A-", "B+", "B", "B-", "C+", "C", "D", "F"));

        return "instructor/grades";
    }

    @PostMapping("/grades/update")
    public String updateGrade(@RequestParam int enrollmentId,
                              @RequestParam(required = false) String grade) {

        if (grade != null && !grade.isBlank()) {
            enrollmentService.assignGrade(enrollmentId, grade);
        }

        return "redirect:/instructor/grades";
    }
}
