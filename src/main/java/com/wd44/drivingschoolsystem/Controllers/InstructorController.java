package com.wd44.drivingschoolsystem.Controllers;

import com.wd44.drivingschoolsystem.DTOs.InstructorCreateDTO;
import com.wd44.drivingschoolsystem.Models.Instructor;
import com.wd44.drivingschoolsystem.Repos.InstructorRepo;
import com.wd44.drivingschoolsystem.Services.InstructorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(path="/instructor")
public class InstructorController {
    @Autowired
    private InstructorRepo instructorRepo;

    @Autowired
    private InstructorService instructorService;

    @PostMapping(path="/add") // Map ONLY POST Requests
    public @ResponseBody String addNewInstructor(@RequestBody InstructorCreateDTO inst) {
        return instructorService.addNewInstructor(inst);
    }

    @DeleteMapping(path="/delete")
    public @ResponseBody String deleteInstructor(@RequestParam int ID) {
        return instructorService.deleteInstructor(ID);
    }

    @GetMapping(path="/getAll")
    public @ResponseBody Iterable<Instructor> getAllInstructors() {
        return instructorRepo.findAll();
    }
}
