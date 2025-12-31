package com.university.reporting.contract.dto;

public class CourseEnrollmentDTO {

    private final Integer courseId;
    private final String courseCode;
    private final Long enrollmentCount;

    public CourseEnrollmentDTO(Integer courseId, String courseCode, Long enrollmentCount) {
        this.courseId = courseId;
        this.courseCode = courseCode;
        this.enrollmentCount = enrollmentCount;
    }

    public Integer getCourseId() { return courseId; }
    public String getCourseCode() { return courseCode; }
    public Long getEnrollmentCount() { return enrollmentCount; }
}
