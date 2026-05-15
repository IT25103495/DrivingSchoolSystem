package com.wd44.drivingschoolsystem.API.Models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.wd44.drivingschoolsystem.API.Enums.vehicleTypes;
import jakarta.persistence.*;

import java.time.LocalDate;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Entity
public class Lesson {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer lessonID;
    //private int lessonNumber;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "studentID")
    private Student student;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "instructorID")
    private Instructor instructor;

    @Column(name = "Vehicle Type")
    private vehicleTypes vehicleType;

    @Column(name = "Lesson Date")
    private LocalDate lessonDate;
//    private LocalTime lessonTime;

    @Column(name = "Grade")
    private char grade;
    @Column(name = "Feedback")
    private String feedback;

    public Integer getLessonID() {
        return lessonID;
    }
//    public int getLessonNumber() {
//        return lessonNumber;
//    }
    public Student getStudent() {
        return student;
    }
    public Instructor getInstructor() {
        return instructor;
    }
    public LocalDate getLessonDate() {
        return lessonDate;
    }
//    public LocalTime getLessonTime() {
//        return lessonTime;
//    }
    public char getGrade() {
        return grade;
    }
    public String getFeedback() {
        return feedback;
    }
    public String getVehicleType() {
        return vehicleType.toString();
    }

    public void setLessonID(Integer lessonID) {
        this.lessonID = lessonID;
    }
//    public void setLessonNumber(int lessonNumber) {
//        this.lessonNumber = lessonNumber;
//    }
    public void setStudent(Student student) {
        this.student = student;
    }
    public void setInstructor(Instructor instructor) {
        this.instructor = instructor;
    }
    public void setLessonDate(LocalDate lessonDate) {
        this.lessonDate = lessonDate;
    }
//    public void setLessonTime(LocalTime lessonTime) {
//        this.lessonTime = lessonTime;
//    }
    public void setGrade(char grade) {
        this.grade = grade;
    }
    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }
    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleTypes.valueOf(vehicleType.toUpperCase());
    }
}