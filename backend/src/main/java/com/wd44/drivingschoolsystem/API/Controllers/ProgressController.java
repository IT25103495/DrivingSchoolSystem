package com.wd44.drivingschoolsystem.API.Controllers;

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

    // GET /progress/getByStudent?ID=1
    // Returns progress data for a specific student
    @GetMapping(path = "/getByStudent")
    public @ResponseBody ResponseEntity<Map<String, Object>> getProgressByStudent(@RequestParam Integer ID) {
        return ResponseEntity.ok(progressService.getProgressByStudentId(ID));
    }

    // GET /progress/getAll
    // Returns progress summary for all students (admin view)
    @GetMapping(path = "/getAll")
    public @ResponseBody ResponseEntity<List<Map<String, Object>>> getAllStudentsProgress() {
        return ResponseEntity.ok(progressService.getAllStudentsProgress());
    }
}
