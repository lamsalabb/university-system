package com.university.core.controller;

import com.university.common.entity.Course;
import com.university.core.service.CourseService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/courses")
public class CourseController {

    private final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping
    public List<Course> getAllCourses(){
        return courseService.getAllCourses();
    }

    @PostMapping
    public ResponseEntity<?> createCourse(@RequestBody Course course){
        return new ResponseEntity<>(
                Map.of("message", "Course successfully created!"),
                HttpStatus.CREATED

        );
    }

    public ResponseEntity<?> deleteCourse(@PathVariable int id){
        courseService.deleteCourse(courseService.findCourseById(id));
    }



}
