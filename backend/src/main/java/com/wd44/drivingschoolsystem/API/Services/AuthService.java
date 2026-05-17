package com.wd44.drivingschoolsystem.API.Services;

import com.wd44.drivingschoolsystem.API.Auth.JwtUtil;
import com.wd44.drivingschoolsystem.API.DTOs.Auth.TokenResponse;
import com.wd44.drivingschoolsystem.API.DTOs.Auth.authLoginDTO;
import com.wd44.drivingschoolsystem.API.DTOs.Student.studentCreateDTO;
import com.wd44.drivingschoolsystem.API.Enums.userType;
import com.wd44.drivingschoolsystem.API.Models.AuthEntity;
import com.wd44.drivingschoolsystem.API.Models.Student;
import com.wd44.drivingschoolsystem.API.Repos.AuthEntityRepository;
import com.wd44.drivingschoolsystem.API.Repos.StudentRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
public class AuthService {
    @Autowired
    AuthEntityRepository authRepo;
    @Autowired
    StudentRepo studentRepo;
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

    @Transactional
    public @ResponseBody String register(studentCreateDTO _std) {
        String phoneNum = _std.getPhoneNum();
        if (phoneNum == null || !phoneNum.matches("\\d{10}")) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Phone number must be exactly 10 digits"
            );
        }

        Student std = new Student();
        AuthEntity auth = new AuthEntity();

        std.setFullName(_std.getFullName());
        std.setDob(_std.getDob());
        std.setPhoneNum(_std.getPhoneNum());
        std.setAuthEntity(auth);

        auth.setUsername(_std.getUsername());
        auth.setPassword(encoder.encode(_std.getPassword()));
        auth.setEmail(_std.getEmail());
        auth.setUserType(userType.STUDENT);

        authRepo.save(auth);
        studentRepo.save(std);
        return "Registered";
    }
}
