package com.wd44.drivingschoolsystem.API.Abstracts;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.wd44.drivingschoolsystem.API.Models.AuthEntity;
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

    @Column(name = "Phone Number")
    protected String phoneNum;

    @JsonIgnore
    @OneToOne
    @JoinColumn(name = "authId", nullable = false)
    private AuthEntity authEntity;

    public Integer getID() {
        return ID;
    }
    public String getFullName() {
        return fullName;
    }
    public LocalDate getDob() {
        return dob;
    }
    public String getPhoneNum() {
        return phoneNum;
    }
    public AuthEntity getAuthEntity() {return authEntity;}

    public void setID(Integer ID) {
        this.ID = ID;
    }
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
    public void setDob(LocalDate dob) {
        this.dob = dob;
    }
    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }
    public void setAuthEntity(AuthEntity authEntity) {
        this.authEntity = authEntity;
    }
}
