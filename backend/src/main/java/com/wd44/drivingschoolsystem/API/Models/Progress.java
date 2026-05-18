package com.wd44.drivingschoolsystem.API.Models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import java.time.LocalDate;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Entity
public class Progress {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer progressID;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "studentID")
    private Student student;

    @Column(name = "Total Lessons")
    private int totalLessons;

    @Column(name = "Completed Lessons")
    private int completedLessons;

    @Column(name = "Pending Lessons")
    private int pendingLessons;

    @Column(name = "Progress Percentage")
    private int progressPercentage;

    @Column(name = "Last Updated")
    private LocalDate lastUpdated;

    // Getters
    public Integer getProgressID() { return progressID; }
    public Student getStudent() { return student; }
    public int getTotalLessons() { return totalLessons; }
    public int getCompletedLessons() { return completedLessons; }
    public int getPendingLessons() { return pendingLessons; }
    public int getProgressPercentage() { return progressPercentage; }
    public LocalDate getLastUpdated() { return lastUpdated; }

    // Setters
    public void setProgressID(Integer progressID) { this.progressID = progressID; }
    public void setStudent(Student student) { this.student = student; }
    public void setTotalLessons(int totalLessons) { this.totalLessons = totalLessons; }
    public void setCompletedLessons(int completedLessons) { this.completedLessons = completedLessons; }
    public void setPendingLessons(int pendingLessons) { this.pendingLessons = pendingLessons; }
    public void setProgressPercentage(int progressPercentage) { this.progressPercentage = progressPercentage; }
    public void setLastUpdated(LocalDate lastUpdated) { this.lastUpdated = lastUpdated; }
}
