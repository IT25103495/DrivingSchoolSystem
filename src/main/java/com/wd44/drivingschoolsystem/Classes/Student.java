package com.wd44.drivingschoolsystem.Classes;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class Student {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer studentID;

    private String fullName;

    private LocalDate DoB;

    private String email;

    private String phoneNum;

    private String userName;
    private String password;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "instructorID")
    private Instructor instructor;

    //private String medicalDocPath;

    public Integer getStudentID() {
        return studentID;
    }
    public String getFullName() {
        return fullName;
    }
    public LocalDate getDoB() {
        return DoB;
    }
    public String getEmail() {
        return email;
    }
    public String getPhoneNum() {
        return phoneNum;
    }
    public String getUserName() {
        return userName;
    }
    public String getPassword() {
        return password;
    }

    public void setStudentID(Integer studentID) {
        this.studentID = studentID;
    }
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
    public void setDoB(LocalDate doB) {
        DoB = doB;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }
    public void setPassword(String password) {
        this.password = password;
    }
}
