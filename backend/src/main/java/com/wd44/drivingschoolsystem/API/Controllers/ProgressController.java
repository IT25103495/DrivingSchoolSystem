package com.wd44.drivingschoolsystem.API.Controllers;

import com.wd44.drivingschoolsystem.API.DTOs.Progress.ProgressCreateDTO;
import com.wd44.drivingschoolsystem.API.DTOs.Progress.ProgressUpdateDTO;
import com.wd44.drivingschoolsystem.API.Models.Progress;
import com.wd44.drivingschoolsystem.API.Services.ProgressService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
@CrossOrigin
@SecurityRequirement(name = "bearerAuth")
@RequestMapping(path = "/progress")
public class ProgressController {

    @Autowired
    private ProgressService progressService;

    // CREATE - POST /progress/add
    @PostMapping(path = "/add")
    public @ResponseBody String addProgress(@RequestBody ProgressCreateDTO progress) {
        return progressService.addProgress(progress);
    }

    // READ - GET /progress/getByStudent?ID=1
    @GetMapping(path = "/getByStudent")
    public @ResponseBody ResponseEntity<Progress> getProgressByStudent(@RequestParam Integer ID) {
        return ResponseEntity.ok(progressService.getProgressByStudentId(ID));
    }

    // READ - GET /progress/getAll
    @GetMapping(path = "/getAll")
    public @ResponseBody Iterable<Progress> getAllStudentsProgress() {
        return progressService.getAllStudentsProgress();
    }

    // UPDATE - PUT /progress/update?ID=1
    @PutMapping(path = "/update")
    public @ResponseBody String updateProgress(@RequestParam int ID, @RequestBody ProgressUpdateDTO progress) {
        return progressService.updateProgress(ID, progress);
    }

    // DELETE - DELETE /progress/delete?ID=1
    @DeleteMapping(path = "/delete")
    public @ResponseBody String deleteProgress(@RequestParam int ID) {
        return progressService.deleteProgress(ID);
    }
}