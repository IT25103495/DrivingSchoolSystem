package com.wd44.drivingschoolsystem.Abstracts;

import jakarta.persistence.*;

import java.time.LocalDate;

@MappedSuperclass
public abstract class User {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    protected Integer ID;

    @Column(name = "Full Name")
    protected String fullName;

    @Column(name = "Date of Birth")
    protected LocalDate dob;

    @Column(name = "Email")
    protected String email;

    @Column(name = "Phone Number")
    protected String phoneNum;

    @Column(name = "Username")
    protected String userName;
    @Column(name = "Password")
    protected String password;

    public Integer getID() {
        return ID;
    }
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
    public String getUserName() {
        return userName;
    }
    public String getPassword() {
        return password;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
    public void setDob(LocalDate dob) {
        this.dob = dob;
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
