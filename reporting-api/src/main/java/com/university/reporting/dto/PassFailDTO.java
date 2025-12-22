package com.university.reporting.dto;

public class PassFailDTO {

    private Integer courseId;
    private String courseCode;
    private Long passCount;
    private Long failCount;

    public PassFailDTO(Integer courseId, String courseCode, Long passCount, Long failCount) {
        this.courseId = courseId;
        this.courseCode = courseCode;
        this.passCount = passCount;
        this.failCount = failCount;
    }

    public Integer getCourseId() { return courseId; }
    public String getCourseCode() { return courseCode; }
    public Long getPassCount() { return passCount; }
    public Long getFailCount() { return failCount; }
}
