package com.wd44.drivingschoolsystem.API.Controllers;

import com.wd44.drivingschoolsystem.API.DTOs.Instructor.InstructorCreateDTO;
import com.wd44.drivingschoolsystem.API.DTOs.Instructor.instructorUpdateDTO;
import com.wd44.drivingschoolsystem.API.Models.Instructor;
import com.wd44.drivingschoolsystem.API.Repos.InstructorRepo;
import com.wd44.drivingschoolsystem.API.Services.InstructorService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@CrossOrigin
@SecurityRequirement(name = "bearerAuth")
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

    @PutMapping(path="/update")
    public @ResponseBody String updateInstructor(@RequestParam int ID, @RequestBody instructorUpdateDTO std) {
        return instructorService.updateInstructor(ID, std);
    }

    @DeleteMapping(path="/delete")
    public @ResponseBody String deleteInstructor(@RequestParam int ID) {
        return instructorService.deleteInstructor(ID);
    }

    @GetMapping(path="/getAll")
    public @ResponseBody Iterable<Instructor> getAllInstructors() {
        return instructorRepo.findAll();
    }

    @GetMapping(path="/getById")
    public @ResponseBody ResponseEntity<Instructor> getInstructorByID(@RequestParam int ID) {
        return instructorRepo.findById(ID)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
