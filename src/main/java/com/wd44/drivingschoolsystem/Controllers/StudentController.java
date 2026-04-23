package com.wd44.drivingschoolsystem.Controllers;

import com.wd44.drivingschoolsystem.Classes.Student;
import com.wd44.drivingschoolsystem.Repos.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller // This means that this class is a Controller
@RequestMapping(path="/student") // This means URL's start with /demo (after Application path)
public class StudentController {
    @Autowired
    private StudentRepository StudentRepository;

    @PostMapping(path="/add") // Map ONLY POST Requests
    public @ResponseBody String addNewUser (@RequestParam String name, @RequestParam String email) {
        Student std = new Student();
        std.setFullName(name);
        std.setEmail(email);
        StudentRepository.save(std);
        return "Saved";
    }

    @GetMapping(path="/all")
    public @ResponseBody Iterable<Student> getAllStudents() {
        return StudentRepository.findAll();
    }
}