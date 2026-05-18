package com.wd44.drivingschoolsystem.API.DTOs.Progress;

public class ProgressCreateDTO {
    private Integer studentId;

    // Constructors
    public ProgressCreateDTO() {}

    public ProgressCreateDTO(Integer studentId) {
        this.studentId = studentId;
    }

    // Getters and Setters
    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }
}
