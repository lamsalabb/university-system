package com.university.reporting.dto;

public class AverageGradeDTO {

    private Integer courseId;
    private String courseCode;
    private Double averageGrade;

    // 🚨 EXACT match for JPQL constructor
    public AverageGradeDTO(
            Integer courseId,
            String courseCode,
            Double averageGrade
    ) {
        this.courseId = courseId;
        this.courseCode = courseCode;
        this.averageGrade = averageGrade;
    }

    public Integer getCourseId() {
        return courseId;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public Double getAverageGrade() {
        return averageGrade;
    }
}
