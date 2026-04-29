package com.wd44.drivingschoolsystem.Models;

import com.wd44.drivingschoolsystem.Abstracts.User;
import jakarta.persistence.*;

@Entity
public class Student extends User {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "instructorID")
    private Instructor instructor;

    //private String medicalDocPath;
}
