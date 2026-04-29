package com.wd44.drivingschoolsystem.Controllers;

import com.wd44.drivingschoolsystem.Models.Student;
import com.wd44.drivingschoolsystem.Repos.StudentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@Controller // This means that this class is a Controller
@RequestMapping(path="/student") // This means URL's start with /demo (after Application path)
public class StudentController {
    @Autowired
    private StudentRepo StudentRepository;

    @PostMapping(path="/add") // Map ONLY POST Requests
    public @ResponseBody String addNewStudent(@RequestParam String name, @RequestParam String email, @RequestParam LocalDate dob, @RequestParam String phoneNum) {
        Student std = new Student();
        std.setFullName(name);
        std.setDoB(dob);
        std.setEmail(email);
        std.setPhoneNum(phoneNum);
        StudentRepository.save(std);
        return "Saved";
    }

    @GetMapping(path="/all")
    public @ResponseBody Iterable<Student> getAllStudents() {
        return StudentRepository.findAll();
    }
}