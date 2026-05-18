package com.wd44.drivingschoolsystem.API.Services;

import com.wd44.drivingschoolsystem.API.DTOs.Student.studentCreateDTO;
import com.wd44.drivingschoolsystem.API.DTOs.Student.studentUpdateDTO;
import com.wd44.drivingschoolsystem.API.Enums.userType;
import com.wd44.drivingschoolsystem.API.Models.AuthEntity;
import com.wd44.drivingschoolsystem.API.Models.Student;
import com.wd44.drivingschoolsystem.API.Repos.AuthEntityRepository;
import com.wd44.drivingschoolsystem.API.Repos.StudentRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.server.ResponseStatusException;

import java.util.Objects;

@Service
public class StudentService {
    @Autowired
    StudentRepo studentRepo;

    @Autowired
    AuthEntityRepository authRepo;

    @Autowired
    PasswordEncoder encoder;

    //Register Student
    @Transactional
    public @ResponseBody String addNewStudent(studentCreateDTO _std) {

        Student std = new Student();
        AuthEntity auth = new AuthEntity();

        std.setFullName(_std.getFullName());
        std.setDob(_std.getDob());
        auth.setEmail(_std.getEmail());
        std.setPhoneNum(_std.getPhoneNum());
        auth.setUsername(_std.getUsername());
        auth.setPassword(encoder.encode(_std.getPassword()));

        std.setAuthEntity(auth);
        auth.setUserType(userType.STUDENT);
        authRepo.save(auth);
        studentRepo.save(std);
        return "Saved";
    }



    //Redundant code and wasted database lookup doing existsBy and then findBy, this was done to return a nice message besides 'internal server error'
    //See if there's a workaround
    //FIXME: [2] Changed to return 404 not found, if that is hard to work with will change back or further modify
    //Update Student
    @Transactional
    public @ResponseBody String updateStudent(int ID, studentUpdateDTO _std) {
        Student std = studentRepo.findById(ID).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Student Not Found!"));
        AuthEntity auth = std.getAuthEntity();
        std.setFullName((Objects.equals(_std.getFullName(), "noChange")) ? std.getFullName() : _std.getFullName());
        std.setDob((_std.getDob().toString().equals("1920-01-01")) ? std.getDob() : _std.getDob());
        auth.setEmail((Objects.equals(_std.getEmail(), "noChange")) ? auth.getEmail() : _std.getEmail());
        std.setPhoneNum((Objects.equals(_std.getPhoneNum(), "noChange")) ? std.getPhoneNum() : _std.getPhoneNum());
        auth.setUsername((Objects.equals(_std.getUsername(), "noChange")) ? auth.getUsername() : _std.getUsername());
        auth.setPassword((Objects.equals(_std.getPassword(), "noChange")) ? auth.getPassword() : encoder.encode(_std.getPassword()));
        authRepo.save(auth);
        studentRepo.save(std);
        return "Updated";
    }
    //Delete Student
    @Transactional
    public @ResponseBody String deleteStudent(int ID) {
        if (studentRepo.existsById(ID)) {
            studentRepo.deleteById(ID);
            return "Deleted";
        }
        else return "Not Found";
    }
}
