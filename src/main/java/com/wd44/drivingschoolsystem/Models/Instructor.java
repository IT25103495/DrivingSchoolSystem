package com.wd44.drivingschoolsystem.Models;

import com.wd44.drivingschoolsystem.Abstracts.User;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class Instructor extends User {

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "instructor")
    private List<Student> studentList;

}
