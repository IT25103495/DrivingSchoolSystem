package com.wd44.drivingschoolsystem.API.Services;

import com.wd44.drivingschoolsystem.API.DTOs.Instructor.InstructorCreateDTO;
import com.wd44.drivingschoolsystem.API.DTOs.Instructor.instructorUpdateDTO;
import com.wd44.drivingschoolsystem.API.Enums.userType;
import com.wd44.drivingschoolsystem.API.Models.AuthEntity;
import com.wd44.drivingschoolsystem.API.Models.Instructor;
import com.wd44.drivingschoolsystem.API.Repos.AuthEntityRepository;
import com.wd44.drivingschoolsystem.API.Repos.InstructorRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.server.ResponseStatusException;

import java.util.Objects;

@Service
public class InstructorService {
    @Autowired
    InstructorRepo instructorRepo;

    @Autowired
    AuthEntityRepository authRepo;

    @Autowired
    PasswordEncoder encoder;

    @Transactional
    public @ResponseBody String addNewInstructor(InstructorCreateDTO _inst) {
        Instructor inst = new Instructor();
        AuthEntity auth = new AuthEntity();
        inst.setFullName(_inst.getFullName());
        inst.setDob(_inst.getDob());
        auth.setEmail(_inst.getEmail());
        inst.setPhoneNum(_inst.getPhoneNum());
        auth.setUsername(_inst.getUsername());
        auth.setPassword(encoder.encode(_inst.getPassword()));
        inst.setAuthEntity(auth);
        auth.setUserType(userType.INSTRUCTOR);
        authRepo.save(auth);
        instructorRepo.save(inst);
        return "Saved";
    }

    @Transactional
    public @ResponseBody String updateInstructor(int ID, instructorUpdateDTO _inst) {
        Instructor inst = instructorRepo.findById(ID).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Instructor Not Found!"));
        AuthEntity auth = inst.getAuthEntity();
        inst.setFullName((Objects.equals(_inst.getFullName(), "noChange")) ? inst.getFullName() : _inst.getFullName());
        inst.setDob((_inst.getDob().toString().equals("1920-01-01")) ? inst.getDob() : _inst.getDob());
        auth.setEmail((Objects.equals(_inst.getEmail(), "noChange")) ? auth.getEmail() : _inst.getEmail());
        inst.setPhoneNum((Objects.equals(_inst.getPhoneNum(), "noChange")) ? inst.getPhoneNum() : _inst.getPhoneNum());
        auth.setUsername((Objects.equals(_inst.getUsername(), "noChange")) ? auth.getUsername() : _inst.getUsername());
        auth.setPassword((Objects.equals(_inst.getPassword(), "noChange")) ? auth.getPassword() : encoder.encode(_inst.getPassword()));
        authRepo.save(auth);
        instructorRepo.save(inst);
        return "Updated";
    }

    @Transactional
    public @ResponseBody String deleteInstructor(int ID) {
        if (instructorRepo.existsById(ID)) {
            instructorRepo.deleteById(ID);
            return "Deleted";
        }
        else return "Not Found";
    }
}
