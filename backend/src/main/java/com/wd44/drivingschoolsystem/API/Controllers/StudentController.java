package com.wd44.drivingschoolsystem.API.Controllers;

import com.wd44.drivingschoolsystem.API.Models.Student;
import com.wd44.drivingschoolsystem.API.Repos.StudentRepo;
import com.wd44.drivingschoolsystem.API.Services.StudentService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@Controller
@CrossOrigin
@SecurityRequirement(name = "bearerAuth")
@RequestMapping(path="/student")
public class StudentController {
    @Autowired
    private StudentRepo studentRepo;

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

    //FIXME: [3] Bro we can't be returning the username and password, make a student return DTO
    @GetMapping(path="/getAll")
    public @ResponseBody Iterable<Student> getAllStudents() {
        return studentRepo.findAll();
    }

    //use this as reference if I need better response bodies/codes later
    @GetMapping(path="/getById")
    public @ResponseBody ResponseEntity<Student> getStudentByID(@RequestParam int ID) {
        return studentRepo.findById(ID)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}