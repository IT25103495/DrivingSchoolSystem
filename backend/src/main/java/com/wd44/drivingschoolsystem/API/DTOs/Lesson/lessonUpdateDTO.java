package com.wd44.drivingschoolsystem.API.DTOs.Lesson;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Id;

import java.time.LocalDate;

public class lessonUpdateDTO {
    @Id
    private int lessonNumber;

    @Schema(defaultValue = "0")
    private Integer studentID = 0;

    @Schema(defaultValue = "0")
    private Integer instructorID = 0;

    @Schema(defaultValue = "noChange")
    private String vehicleType = "noChange";

    @Schema(defaultValue = "1920-01-01")
    private LocalDate lessonDate = LocalDate.parse("1920-01-01");;
    //private LocalTime lessonTime;

    @Schema(defaultValue = "Z")
    private char grade = 'Z';
    @Schema(defaultValue = "noChange")
    private String feedback = "noChange";

    //public int getLessonNumber() {
    //    return lessonNumber;
    //}

    public Integer getStudentID() {
        return studentID;
    }
    public Integer getInstructorID() {
        return instructorID;
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
        return vehicleType;
    }

//    public void setLessonID(Integer lessonID) {
//        this.lessonID = lessonID;
//    }
//    public void setLessonNumber(int lessonNumber) {
//        this.lessonNumber = lessonNumber;
//    }
//    public void setStudent(Student student) {
//        this.student = student;
//    }
//    public void setInstructor(Instructor instructor) {
//        this.instructor = instructor;
//    }
//    public void setLessonDate(LocalDate lessonDate) {
//        this.lessonDate = lessonDate;
//    }
//    public void setLessonTime(LocalDateTime lessonTime) {
//        this.lessonTime = lessonTime;
//    }
//    public void setGrade(char grade) {
//        this.grade = grade;
//    }
//    public void setFeedback(String feedback) {
//        this.feedback = feedback;
//    }
//    public void setVehicleType(String vehicleType) {
//        this.vehicleType = vehicleTypes.valueOf(vehicleType.toUpperCase());
//    }
}