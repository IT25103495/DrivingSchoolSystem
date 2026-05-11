package com.wd44.drivingschoolsystem.API.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*")
public class AuthControllerEdited {

    @Autowired private com.wd44.drivingschoolsystem.service.AuthServiceEdited authService;

    // POST /api/auth/login
    // Body: { "username": "ashan", "password": "stu123" }
    // Returns: { "userType":"student", "id":1, "fullName":"...", ... }
    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> login(
            @RequestBody Map<String, String> body) {
        return ResponseEntity.ok(authService.login(
                body.get("username"), body.get("password")));
    }
}
