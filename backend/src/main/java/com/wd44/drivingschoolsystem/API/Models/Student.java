package com.wd44.drivingschoolsystem.API.Models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.wd44.drivingschoolsystem.API.Abstracts.User;
import jakarta.persistence.*;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
//FIXME: [2] Hacky solution, return type DTO is more effective
@Entity
public class Student extends User {

    //@ManyToOne(fetch = FetchType.LAZY)
    //@JoinColumn(name = "instructorID")
    //private Instructor instructor;

    //private String medicalDocPath;
}
