package com.university.reporting.dto;

public class EnrollmentTrendDTO {

    private String semester;
    private Long enrollmentCount;

    public EnrollmentTrendDTO(String semester, Long enrollmentCount) {
        this.semester = semester;
        this.enrollmentCount = enrollmentCount;
    }

    public String getSemester() { return semester; }
    public Long getEnrollmentCount() { return enrollmentCount; }
}
