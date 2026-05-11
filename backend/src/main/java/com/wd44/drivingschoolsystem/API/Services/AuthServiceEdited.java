package com.wd44.drivingschoolsystem.API.Services;

import com.drivingschool.driving_school.model.Instructor;
import com.drivingschool.driving_school.model.Student;
import com.drivingschool.driving_school.repository.InstructorRepository;
import com.drivingschool.driving_school.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.Map;

@Service
public class AuthServiceEdited {

    @Autowired private StudentRepository studentRepository;
    @Autowired private InstructorRepository instructorRepository;

    // Single login for both students and instructors
    public Map<String, Object> login(String username, String password) {

        // Check students table first
        Student s = studentRepository.findByUsername(username);
        if (s != null && s.getPassword().equals(password)) {
            Map<String, Object> res = new HashMap<>();
            res.put("userType",  "student");
            res.put("id",        s.getId());
            res.put("fullName",  s.getFullName());
            res.put("classType", s.getClassType());
            res.put("email",     s.getEmail());
            return res;
        }

        // Check instructors table
        Instructor i = instructorRepository.findByUsername(username);
        if (i != null && i.getPassword() != null && i.getPassword().equals(password)) {
            Map<String, Object> res = new HashMap<>();
            res.put("userType",  "instructor");
            res.put("id",        i.getId());
            res.put("fullName",  i.getFullName());
            res.put("classType", i.getClassType());
            res.put("email",     i.getEmail());
            return res;
        }

        throw new RuntimeException("Invalid username or password");
    }
}
