package com.university.core.mvccontroller.instructor;

import com.university.common.entity.Attendance;
import com.university.common.entity.Course;
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

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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

        List<Attendance> attendanceList =
                enrollments.isEmpty()
                        ? List.of()
                        : attendanceService.getAttendanceForEnrollments(enrollments);

        model.addAttribute("attendances", attendanceList);
        return "instructor/attendance/attendance";
    }


    @PostMapping("/attendance/update")
    public String updateAttendance(@RequestParam int attendanceId,
                                   @RequestParam Attendance.Status status) {

        attendanceService.updateStatus(attendanceId, status);
        return "redirect:/instructor/attendance";
    }


    @GetMapping("/attendance/select-course")
    public String selectCourse(Authentication authentication, Model model) {

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        User instructor = userService.findUserByEmail(userDetails.getUsername());

        List<Enrollment> enrollments =
                enrollmentService.getEnrollmentsByInstructor(instructor.getId());

        List<Course> courses = enrollments.stream()
                .map(Enrollment::getCourse)
                .collect(Collectors.collectingAndThen(
                        Collectors.toMap(
                                Course::getId,
                                c -> c,
                                (a, b) -> a
                        ),
                        m -> m.values().stream().toList()
                ));

        model.addAttribute("courses", courses);
        return "instructor/attendance/attendance-select-course";
    }


    @GetMapping("/attendance/mark/{courseId}")
    public String markAttendancePage(@PathVariable int courseId,
                                     Model model) {

        List<Enrollment> enrollments =
                enrollmentService.getActiveEnrollmentsByCourse(courseId);

        model.addAttribute("enrollments", enrollments);
        model.addAttribute("today", LocalDate.now());
        model.addAttribute("courseId", courseId);

        return "instructor/attendance/attendance-mark";
    }

    @PostMapping("/attendance/mark/save")
    public String saveAttendance(@RequestParam int courseId,
                                 @RequestParam Map<String, String> params) {

        LocalDate today = LocalDate.now();

        params.forEach((key, value) -> {
            if (key.startsWith("status_")) {

                int enrollmentId =
                        Integer.parseInt(key.replace("status_", ""));

                Attendance.Status status =
                        Attendance.Status.valueOf(value);

                attendanceService.markAttendance(
                        enrollmentId,
                        today,
                        status,
                        null
                );
            }
        });

        return "redirect:/instructor/attendance";
    }
}
