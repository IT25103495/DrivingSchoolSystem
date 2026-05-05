package com.wd44.drivingschoolsystem.Models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.wd44.drivingschoolsystem.Abstracts.User;
import jakarta.persistence.*;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Entity
public class Instructor extends User {

    //@OneToMany(fetch = FetchType.LAZY, mappedBy = "instructor")
    //private List<Student> studentList;

}
