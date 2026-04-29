package com.wd44.drivingschoolsystem.Services;

import com.wd44.drivingschoolsystem.DTOs.studentCreateDTO;
import com.wd44.drivingschoolsystem.Models.Student;
import com.wd44.drivingschoolsystem.Repos.StudentRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseBody;

@Service
public class StudentService {
    @Autowired
    StudentRepo studentRepo;

    @Transactional
    public @ResponseBody String addNewStudent(studentCreateDTO _std) {
        Student std = new Student();
        std.setFullName(_std.getFullName());
        std.setDob(_std.getDob());
        std.setEmail(_std.getEmail());
        std.setPhoneNum(_std.getPhoneNum());
        std.setUserName(_std.getUserName());
        std.setPassword(_std.getPassword());
        studentRepo.save(std);
        return "Saved";
    }
}
