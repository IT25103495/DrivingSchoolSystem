package com.wd44.drivingschoolsystem.API.DTOs.Progress;

import io.swagger.v3.oas.annotations.media.Schema;

public class progressUpdateDTO {

    protected Integer progressID;

    @Schema(defaultValue = "0")
    protected int completedLessons = 0;

    public Integer getProgressID() { return progressID; }
    public int getCompletedLessons() { return completedLessons; }

    public void setProgressID(Integer progressID) { this.progressID = progressID; }
    public void setCompletedLessons(int completedLessons) { this.completedLessons = completedLessons; }
}
