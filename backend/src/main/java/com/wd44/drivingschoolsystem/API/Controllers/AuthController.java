package com.wd44.drivingschoolsystem.API.Controllers;

import com.wd44.drivingschoolsystem.API.DTOs.Auth.TokenResponse;
import com.wd44.drivingschoolsystem.API.DTOs.Auth.authLoginDTO;
import com.wd44.drivingschoolsystem.API.Services.AuthService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@CrossOrigin
@SecurityRequirement(name = "bearerAuth")
@RequestMapping(path="/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping(path="/login") // Map ONLY POST Requests
    public @ResponseBody TokenResponse login(@RequestBody authLoginDTO user) {
        return authService.login(user);
    }

    @PostMapping(path="/register")
    public @ResponseBody String register(@RequestBody studentCreateDTO user) {
        return authService.register(user);
    }

//    @PreAuthorize("hasRole('ROLE_STUDENT')")
//    @GetMapping(path="/logintest")
//    public @ResponseBody ResponseEntity<Object> testLogin() {
//
//        return ResponseEntity.ok(SecurityContextHolder.getContext()
//                .getAuthentication().getPrincipal());
//    }

}
