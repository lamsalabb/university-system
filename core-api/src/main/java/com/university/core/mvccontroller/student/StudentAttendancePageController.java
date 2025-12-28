package com.university.core.mvccontroller.student;

import com.university.common.entity.User;
import com.university.core.service.AttendanceService;
import com.university.core.service.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/student")
public class StudentAttendancePageController {

    private final AttendanceService attendanceService;
    private final UserService userService;

    public StudentAttendancePageController(AttendanceService attendanceService,
                                           UserService userService) {
        this.attendanceService = attendanceService;
        this.userService = userService;
    }

    @GetMapping("/attendance")
    public String attendance(Authentication auth, Model model) {

        String email = auth.getName();
        User student = userService.findUserByEmail(email);

        model.addAttribute(
                "attendanceList",
                attendanceService.getAttendanceByStudent(student.getId())
        );

        return "student/attendance";
    }
}
