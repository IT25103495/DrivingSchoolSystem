package com.wd44.drivingschoolsystem.API.Controllers;

import com.wd44.drivingschoolsystem.API.DTOs.ApiResponseDTO;
import com.wd44.drivingschoolsystem.API.DTOs.Progress.ProgressCreateDTO;
import com.wd44.drivingschoolsystem.API.DTOs.Progress.ProgressResponseDTO;
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
    @PostMapping("/add")
    public ResponseEntity<ApiResponseDTO<ProgressResponseDTO>> addProgress(
            @RequestBody ProgressCreateDTO dto) {

        return ResponseEntity.ok(progressService.addProgress(dto));
    }

    // READ - GET /progress/getByStudent?ID=1
    @GetMapping("/student/{studentId}")
    public ResponseEntity<ApiResponseDTO<ProgressResponseDTO>> getByStudent(
            @PathVariable Integer studentId) {

        return ResponseEntity.ok(progressService.getProgressByStudentId(studentId));
    }

    // READ - GET /progress/getAll
    @GetMapping("/all")
    public ResponseEntity<ApiResponseDTO<List<ProgressResponseDTO>>> getAll() {

        return ResponseEntity.ok(progressService.getAllStudentsProgress());
    }

    // UPDATE - PUT /progress/update?ID=1
    @PutMapping("/update")
    public ResponseEntity<ApiResponseDTO<ProgressResponseDTO>> update(
            @RequestBody ProgressUpdateDTO dto) {

        return ResponseEntity.ok(progressService.updateProgress(dto));
    }

    // DELETE - DELETE /progress/delete?ID=1
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ApiResponseDTO<String>> delete(
            @PathVariable Integer id) {

        return ResponseEntity.ok(progressService.deleteProgress(id));
    }
}