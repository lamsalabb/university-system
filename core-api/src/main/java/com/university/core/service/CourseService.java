package com.university.core.service;

import com.university.common.entity.Course;
import com.university.common.repository.CourseRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.security.PublicKey;
import java.util.List;

@Service
public class CourseService {

    private final CourseRepository courseRepository;

    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }



    @Transactional//CREATE
    public Course createCourse(Course course){
        if(courseRepository.findByCode(course.getCode()).isPresent()){
            throw new RuntimeException("Course with code "+course.getCode()+" already exists.");
        }
        return courseRepository.save(course);
    }




    //READ
    public List<Course> getAllCourses(){
        return courseRepository.findAll();
    }

    public Course findCourseById(int id){
        return courseRepository.findById(id).orElseThrow(() ->new RuntimeException("Course not found with id: "+id));
    }


    @Transactional//UPDATE
    public Course updateCourse(int id, Course updatedCourseDetails){
        Course existingCourse = findCourseById(id);

        existingCourse.setTitle(updatedCourseDetails.getTitle());
        existingCourse.setCode(updatedCourseDetails.getCode());
        existingCourse.setCredits(updatedCourseDetails.getCredits());
        existingCourse.setDescription(updatedCourseDetails.getDescription());
        existingCourse.setInstructor(updatedCourseDetails.getInstructor());

        return courseRepository.save(existingCourse);
    }



    @Transactional//DELETE
    public void deleteCourse(int id){
        if (!courseRepository.existsById(id)){
            throw new RuntimeException("Cannot delete. Course not found with id: " + id);
        }
        courseRepository.deleteById(id);
    }





}
