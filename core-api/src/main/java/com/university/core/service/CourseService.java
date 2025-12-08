package com.university.core.service;

import com.university.common.entity.Course;
import com.university.common.repository.CourseRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseService {

    private final CourseRepository courseRepository;

    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    public List<Course> getAllCourses(){
        return courseRepository.findAll();
    }

    public Course findCourseById(int id){
        return courseRepository.findById(id);
    }

    @Transactional
    public Course createCourse(Course course){
        return courseRepository.save(course);
    }

    @Transactional
    public void deleteCourse(Course course){
        courseRepository.delete(course);
    }





}
