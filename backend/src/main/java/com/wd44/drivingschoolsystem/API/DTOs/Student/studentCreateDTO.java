package com.wd44.drivingschoolsystem.API.DTOs.Student;

import java.time.LocalDate;

public class studentCreateDTO {

    protected String fullName;

    protected LocalDate dob;

    protected String email;

    protected String phoneNum;

    protected String username;
    protected String password;

    public String getFullName() {
        return fullName;
    }



    public LocalDate getDob() {
        return dob;
    }



    public String getEmail() {
        return email;
    }



    public String getPhoneNum() {
        return phoneNum;
    }



    public String getUsername() {
        return username;
    }



    public String getPassword() {
        return password;
    }


}



