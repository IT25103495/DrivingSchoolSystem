package com.wd44.drivingschoolsystem.Abstracts;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;

import java.time.LocalDate;

@MappedSuperclass
public abstract class User {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    protected Integer ID;

    protected String fullName;

    protected LocalDate DoB;

    protected String email;

    protected String phoneNum;

    protected String userName;
    protected String password;

    public Integer getStudentID() {
        return ID;
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

    public void setStudentID(Integer ID) {
        this.ID = ID;
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
