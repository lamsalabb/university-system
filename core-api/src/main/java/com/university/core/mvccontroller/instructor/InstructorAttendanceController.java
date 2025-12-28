package com.university.core.mvccontroller.instructor;

import com.university.common.entity.Attendance;
import com.university.common.entity.Enrollment;
import com.university.common.entity.User;
import com.university.core.service.AttendanceService;
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
public class InstructorAttendanceController {

    private final AttendanceService attendanceService;
    private final EnrollmentService enrollmentService;
    private final UserService userService;

    public InstructorAttendanceController(AttendanceService attendanceService,
                                          EnrollmentService enrollmentService,
                                          UserService userService) {
        this.attendanceService = attendanceService;
        this.enrollmentService = enrollmentService;
        this.userService = userService;
    }

    @GetMapping("/attendance")
    public String viewAttendance(Authentication authentication, Model model) {

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        User instructor = userService.findUserByEmail(userDetails.getUsername());

        List<Enrollment> enrollments =
                enrollmentService.getEnrollmentsByInstructor(instructor.getId());

        List<Attendance> attendanceList = List.of();
        if (!enrollments.isEmpty()) {
            attendanceList = attendanceService.getAttendanceForEnrollments(enrollments);
        }

        // ✅ MUST MATCH HTML
        model.addAttribute("attendances", attendanceList);

        return "instructor/attendance";
    }

    @PostMapping("/attendance/update")
    public String updateAttendance(@RequestParam int attendanceId,
                                   @RequestParam Attendance.Status status) {

        attendanceService.updateStatus(attendanceId, status);
        return "redirect:/instructor/attendance";
    }
}
