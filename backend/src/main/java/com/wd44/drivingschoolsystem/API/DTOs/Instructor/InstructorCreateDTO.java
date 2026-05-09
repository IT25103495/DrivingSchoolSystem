package com.wd44.drivingschoolsystem.API.DTOs.Instructor;

import java.time.LocalDate;

public class InstructorCreateDTO {

    protected String fullName;

    protected LocalDate dob;

    protected String email;

    protected String phoneNum;

    protected String userName;
    protected String password;

    public String getFullName() {
        return fullName;
    }

//    public void setFullName(String fullName) {
//        this.fullName = fullName;
//    }

    public LocalDate getDob() {
        return dob;
    }

//    public void setDob(LocalDate dob) {
//        this.dob = dob;
//    }

    public String getEmail() {
        return email;
    }

//    public void setEmail(String email) {
//        this.email = email;
//    }

    public String getPhoneNum() {
        return phoneNum;
    }

//    public void setPhoneNum(String phoneNum) {
//        this.phoneNum = phoneNum;
//    }

    public String getUserName() {
        return userName;
    }

//    public void setUserName(String userName) {
//        this.userName = userName;
//    }

    public String getPassword() {
        return password;
    }

//    public void setPassword(String password) {
//        this.password = password;
//    }
}
