package com.wd44.drivingschoolsystem.API.DTOs.Progress;

public class ProgressResponseDTO {

    private Integer progressId;
    private Integer studentId;

    private int totalLessons;
    private int completedLessons;
    private int pendingLessons;
    private int progressPercentage;

    private String lastUpdated;


    // Constructors
    public ProgressResponseDTO() {
    }

    public ProgressResponseDTO(Integer progressId, Integer studentId, int totalLessons, int completedLessons, int pendingLessons, int progressPercentage, String lastUpdated) {
        this.progressId = progressId;
        this.studentId = studentId;
        this.totalLessons = totalLessons;
        this.completedLessons = completedLessons;
        this.pendingLessons = pendingLessons;
        this.progressPercentage = progressPercentage;
        this.lastUpdated = lastUpdated;
    }


    // getters and setters
    public Integer getProgressId() {
        return progressId;
    }

    public void setProgressId(Integer progressId) {
        this.progressId = progressId;
    }

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    public int getTotalLessons() {
        return totalLessons;
    }

    public void setTotalLessons(int totalLessons) {
        this.totalLessons = totalLessons;
    }

    public int getCompletedLessons() {
        return completedLessons;
    }

    public void setCompletedLessons(int completedLessons) {
        this.completedLessons = completedLessons;
    }

    public int getPendingLessons() {
        return pendingLessons;
    }

    public void setPendingLessons(int pendingLessons) {
        this.pendingLessons = pendingLessons;
    }

    public int getProgressPercentage() {
        return progressPercentage;
    }

    public void setProgressPercentage(int progressPercentage) {
        this.progressPercentage = progressPercentage;
    }

    public String getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(String lastUpdated) {
        this.lastUpdated = lastUpdated;
    }
}