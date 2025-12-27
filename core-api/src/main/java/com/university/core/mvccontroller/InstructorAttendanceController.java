package com.university.core.mvccontroller;

import com.university.common.entity.Attendance;
import com.university.core.service.AttendanceService;
import com.university.core.service.EnrollmentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@Controller
@RequestMapping("/instructor/attendance")
public class InstructorAttendanceController {

    private final AttendanceService attendanceService;
    private final EnrollmentService enrollmentService;

    public InstructorAttendanceController(AttendanceService attendanceService,
                                              EnrollmentService enrollmentService) {
        this.attendanceService = attendanceService;
        this.enrollmentService = enrollmentService;
    }

    @GetMapping("/course/{courseId}")
    public String page(@PathVariable int courseId, Model model) {

        model.addAttribute("enrollments",
                enrollmentService.getEnrollmentByCourse(courseId));

        model.addAttribute("courseId", courseId);
        return "instructor/attendance";
    }

    @PostMapping("/mark")
    public String mark(@RequestParam int enrollmentId,
                       @RequestParam Attendance.Status status,
                       @RequestParam String remarks,
                       @RequestParam String sessionDate) {

        attendanceService.markAttendance(
                enrollmentId,
                LocalDate.parse(sessionDate),
                status,
                remarks
        );

        return "redirect:/instructor/courses";
    }
}
