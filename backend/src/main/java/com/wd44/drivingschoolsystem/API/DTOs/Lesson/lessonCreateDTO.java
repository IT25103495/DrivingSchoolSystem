package com.wd44.drivingschoolsystem.API.DTOs.Lesson;

import java.time.LocalDate;

public class lessonCreateDTO {
    //private int lessonNumber;

    private Integer studentID;

    private Integer instructorID;

    private String vehicleType;
    // TODO: [2] Consider accepting type vehicleTypes bc it lowkey could still work, adds enum security at request level which is #Banger

    private LocalDate lessonDate;
    //private LocalTime lessonTime;

    // TODO: [1] LocalTime sucks balls so use LocalDateTime and split off date in frontend if I want time back

//    private char grade;
//    private String feedback;

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
//    public char getGrade() {
//        return grade;
//    }
//    public String getFeedback() {
//        return feedback;
//    }
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