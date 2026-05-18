package com.wd44.drivingschoolsystem.API.DTOs.Progress;

import io.swagger.v3.oas.annotations.media.Schema;

public class ProgressUpdateDTO {

    protected Integer studentID;
    protected Integer progressID;

    @Schema(defaultValue = "0")
    protected int completedLessons = 0;

    public  ProgressUpdateDTO(){}

    public ProgressUpdateDTO(Integer studentID, Integer progressID, int completedLessons) {
        this.studentID = studentID;
        this.progressID = progressID;
        this.completedLessons = completedLessons;
    }

    public Integer getStudentId() { return studentID; }
    public Integer getProgressID() { return progressID; }
    public int getCompletedLessons() { return completedLessons; }

    public void setStudentId(Integer studentID) { this.studentID = studentID; }
    public void setProgressID(Integer progressID) { this.progressID = progressID; }
    public void setCompletedLessons(int completedLessons) { this.completedLessons = completedLessons; }
}
