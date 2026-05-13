package com.wd44.drivingschoolsystem.API.Services;

import com.wd44.drivingschoolsystem.API.Auth.JwtUtil;
import com.wd44.drivingschoolsystem.API.DTOs.Auth.TokenResponse;
import com.wd44.drivingschoolsystem.API.DTOs.Auth.authLoginDTO;
import com.wd44.drivingschoolsystem.API.Models.AuthEntity;
import com.wd44.drivingschoolsystem.API.Repos.AuthEntityRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {
    @Autowired
    AuthEntityRepository authRepo;
    @Autowired
    JwtUtil jwtUtil;
    @Autowired
    PasswordEncoder encoder;

    @Transactional
    public TokenResponse login(authLoginDTO request) {
        TokenResponse token = new TokenResponse();

        //Verify user is in DB
        Optional<AuthEntity> opAuth = authRepo.findByUsername(request.getUsername());
        if (opAuth.isEmpty() ||
                !encoder.matches(request.getPassword(), opAuth.get().getPassword())) {

            token.setSuccess(false);
            token.setError("Invalid Username or Password");
        }
        else {
            token.setToken(jwtUtil.generateToken(opAuth.get().getUsername()));
            token.setSuccess(true);
            token.setRole(opAuth.get().getUserType().name());
        }

        return token;
    }
}
