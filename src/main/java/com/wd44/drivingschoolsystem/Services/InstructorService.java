package com.wd44.drivingschoolsystem.Services;

import com.wd44.drivingschoolsystem.DTOs.InstructorCreateDTO;
import com.wd44.drivingschoolsystem.Models.Instructor;
import com.wd44.drivingschoolsystem.Repos.InstructorRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseBody;

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
}
