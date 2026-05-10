package com.wd44.drivingschoolsystem.API.Services;

import com.wd44.drivingschoolsystem.API.DTOs.Instructor.InstructorCreateDTO;
import com.wd44.drivingschoolsystem.API.DTOs.Instructor.instructorUpdateDTO;
import com.wd44.drivingschoolsystem.API.Models.Instructor;
import com.wd44.drivingschoolsystem.API.Repos.InstructorRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.server.ResponseStatusException;

import java.util.Objects;

@Service
public class InstructorService {
    @Autowired
    InstructorRepo instructorRepo;

    @Transactional
    public @ResponseBody String addNewInstructor(InstructorCreateDTO _inst) {
        Instructor inst = new Instructor();
        inst.setFullName(_inst.getFullName());
        inst.setDob(_inst.getDob());
        inst.setEmail(_inst.getEmail());
        inst.setPhoneNum(_inst.getPhoneNum());
        inst.setUserName(_inst.getUserName());
        inst.setPassword(_inst.getPassword());
        instructorRepo.save(inst);
        return "Saved";
    }

    @Transactional
    public @ResponseBody String updateInstructor(int ID, instructorUpdateDTO _std) {
        Instructor std = instructorRepo.findById(ID).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Instructor Not Found!"));
        std.setFullName((Objects.equals(_std.getFullName(), "noChange")) ? std.getFullName() : _std.getFullName());
        std.setDob((_std.getDob().toString().equals("1920-01-01")) ? std.getDob() : _std.getDob());
        std.setEmail((Objects.equals(_std.getEmail(), "noChange")) ? std.getEmail() : _std.getEmail());
        std.setPhoneNum((Objects.equals(_std.getPhoneNum(), "noChange")) ? std.getPhoneNum() : _std.getPhoneNum());
        std.setUserName((Objects.equals(_std.getUserName(), "noChange")) ? std.getUserName() : _std.getUserName());
        std.setPassword((Objects.equals(_std.getPassword(), "noChange")) ? std.getPassword() : _std.getPassword());
        instructorRepo.save(std);
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
