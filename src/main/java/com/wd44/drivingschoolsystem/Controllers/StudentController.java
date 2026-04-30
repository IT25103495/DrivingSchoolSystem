package com.wd44.drivingschoolsystem.Controllers;

import com.wd44.drivingschoolsystem.DTOs.Student.studentCreateDTO;
import com.wd44.drivingschoolsystem.DTOs.Student.studentUpdateDTO;
import com.wd44.drivingschoolsystem.Models.Student;
import com.wd44.drivingschoolsystem.Repos.StudentRepo;
import com.wd44.drivingschoolsystem.Services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@Controller // This means that this class is a Controller
@RequestMapping(path="/student") // This means URL's start with /demo (after Application path)
public class StudentController {
    @Autowired
    private StudentRepo StudentRepository;

    @Autowired
    private StudentService studentService;

    @PostMapping(path="/add") // Map ONLY POST Requests
    public @ResponseBody String addNewStudent(@RequestBody studentCreateDTO std) {
        return studentService.addNewStudent(std);
    }

    @PutMapping(path="/update")
    public @ResponseBody String updateStudent(@RequestParam int ID, @RequestBody studentUpdateDTO std) {
        return studentService.updateStudent(ID, std);
    }

    @DeleteMapping(path="/delete")
    public @ResponseBody String deleteStudent(@RequestParam int ID) {
        return studentService.deleteStudent(ID);
    }

    @GetMapping(path="/getAll")
    public @ResponseBody Iterable<Student> getAllStudents() {
        return StudentRepository.findAll();
    }
}