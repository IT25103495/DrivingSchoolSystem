package com.wd44.drivingschoolsystem.Controllers;

import com.wd44.drivingschoolsystem.DTOs.InstructorCreateDTO;
import com.wd44.drivingschoolsystem.DTOs.studentCreateDTO;
import com.wd44.drivingschoolsystem.Models.Instructor;
import com.wd44.drivingschoolsystem.Models.Student;
import com.wd44.drivingschoolsystem.Repos.InstructorRepo;
import com.wd44.drivingschoolsystem.Repos.StudentRepo;
import com.wd44.drivingschoolsystem.Services.InstructorService;
import com.wd44.drivingschoolsystem.Services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(path="/instructor")
public class InstructorController {
    @Autowired
    private InstructorRepo instRepo;

    @Autowired
    private InstructorService instService;

    @PostMapping(path="/add") // Map ONLY POST Requests
    public @ResponseBody String addNewInstructor(@RequestBody InstructorCreateDTO inst) {
        return instService.addNewInstructor(inst);
    }

    @GetMapping(path="/all")
    public @ResponseBody Iterable<Instructor> getAllInstructors() {
        return instRepo.findAll();
    }
}
