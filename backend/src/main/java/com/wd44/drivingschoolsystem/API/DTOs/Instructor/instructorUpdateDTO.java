package com.wd44.drivingschoolsystem.API.DTOs.Instructor;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Id;

import java.time.LocalDate;

public class instructorUpdateDTO {
    @Id
    protected int ID;

    @Schema(defaultValue = "noChange")
    protected String fullName = "noChange";

    @Schema(defaultValue = "1920-01-01")
    protected LocalDate dob = LocalDate.parse("1920-01-01");

    @Schema(defaultValue = "noChange")
    protected String email = "noChange";

    @Schema(defaultValue = "noChange")
    protected String phoneNum = "noChange";

    @Schema(defaultValue = "noChange")
    protected String userName = "noChange";
    @Schema(defaultValue = "noChange")
    protected String password = "noChange";

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
