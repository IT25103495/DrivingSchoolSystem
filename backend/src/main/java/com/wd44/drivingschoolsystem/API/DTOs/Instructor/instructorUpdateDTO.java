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
    protected String username = "noChange";
    @Schema(defaultValue = "noChange")
    protected String password = "noChange";

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
    
    public String getFullName() {
        return fullName;
    }

     public void setDob(LocalDate dob) {
        this.dob = dob;
    }
    public LocalDate getDob() {
        return dob;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getEmail() {
        return email;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    
    public String getPhoneNum() {
        return phoneNum;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    
    public String getUsername() {
        return username;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    public String getPassword() {
        return password;
    }

}
