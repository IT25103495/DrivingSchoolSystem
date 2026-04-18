package com.wd44.drivingschoolsystem.Classes;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
public class Lesson {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer lessonID;
    private int lessonNumber;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "studentID")
    private Student student;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "instructorID")
    private Instructor instructor;

    private LocalDate lessonDate;
    private LocalDateTime lessonTime;

    private char grade;
    private String feedback;

    public Integer getLessonID() {
        return lessonID;
    }
    public int getLessonNumber() {
        return lessonNumber;
    }
    public Student getStudent() {
        return student;
    }
    public Instructor getInstructor() {
        return instructor;
    }
    public LocalDate getLessonDate() {
        return lessonDate;
    }
    public LocalDateTime getLessonTime() {
        return lessonTime;
    }
    public char getGrade() {
        return grade;
    }
    public String getFeedback() {
        return feedback;
    }

    public void setLessonID(Integer lessonID) {
        this.lessonID = lessonID;
    }
    public void setLessonNumber(int lessonNumber) {
        this.lessonNumber = lessonNumber;
    }
    public void setStudent(Student student) {
        this.student = student;
    }
    public void setInstructor(Instructor instructor) {
        this.instructor = instructor;
    }
    public void setLessonDate(LocalDate lessonDate) {
        this.lessonDate = lessonDate;
    }
    public void setLessonTime(LocalDateTime lessonTime) {
        this.lessonTime = lessonTime;
    }
    public void setGrade(char grade) {
        this.grade = grade;
    }
    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }
}