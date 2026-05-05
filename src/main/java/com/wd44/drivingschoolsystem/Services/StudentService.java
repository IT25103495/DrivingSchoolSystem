package com.wd44.drivingschoolsystem.Services;

import com.wd44.drivingschoolsystem.DTOs.Student.studentCreateDTO;
import com.wd44.drivingschoolsystem.DTOs.Student.studentUpdateDTO;
import com.wd44.drivingschoolsystem.Models.Student;
import com.wd44.drivingschoolsystem.Repos.StudentRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.server.ResponseStatusException;

import java.util.Objects;

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

    //TODO: For the loveee of god make a mapper class pleaseeee (use @Mapper for fun shit)

    //Redundant code and wasted database lookup doing existsBy and then findBy, this was done to return a nice message besides 'internal server error'
    //See if there's a workaround
    //FIXME: Changed to return 404 not found, if that is hard to work with will change back or further modify
    @Transactional
    public @ResponseBody String updateStudent(int ID, studentUpdateDTO _std) {
        Student std = studentRepo.findById(ID).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Student Not Found!"));
        std.setFullName((Objects.equals(_std.getFullName(), "noChange")) ? std.getFullName() : _std.getFullName());
        std.setDob((_std.getDob().toString().equals("1920-01-01")) ? std.getDob() : _std.getDob());
        std.setEmail((Objects.equals(_std.getEmail(), "noChange")) ? std.getEmail() : _std.getEmail());
        std.setPhoneNum((Objects.equals(_std.getPhoneNum(), "noChange")) ? std.getPhoneNum() : _std.getPhoneNum());
        std.setUserName((Objects.equals(_std.getUserName(), "noChange")) ? std.getUserName() : _std.getUserName());
        std.setPassword((Objects.equals(_std.getPassword(), "noChange")) ? std.getPassword() : _std.getPassword());
        studentRepo.save(std);
        return "Updated";
    }

    @Transactional
    public @ResponseBody String deleteStudent(int ID) {
        studentRepo.deleteById(ID);
        return "Deleted";
    }
}
