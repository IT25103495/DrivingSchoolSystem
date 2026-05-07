package com.wd44.drivingschoolsystem.Services;

import com.wd44.drivingschoolsystem.DTOs.Lesson.lessonCreateDTO;
import com.wd44.drivingschoolsystem.DTOs.Lesson.lessonUpdateDTO;
import com.wd44.drivingschoolsystem.Models.Instructor;
import com.wd44.drivingschoolsystem.Models.Lesson;
import com.wd44.drivingschoolsystem.Models.Student;
import com.wd44.drivingschoolsystem.Repos.InstructorRepo;
import com.wd44.drivingschoolsystem.Repos.LessonRepo;
import com.wd44.drivingschoolsystem.Repos.StudentRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.server.ResponseStatusException;

@Service
public class LessonService {
    @Autowired
    LessonRepo lessonRepo;
    @Autowired
    StudentRepo studentRepo;
    @Autowired
    InstructorRepo instructorRepo;

    @Transactional
    public @ResponseBody String addNewLesson(lessonCreateDTO _les) {
        Lesson les = new Lesson();
        Student std = studentRepo.findById(_les.getStudentID()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Student Not Found!"));
        Instructor inst = instructorRepo.findById(_les.getInstructorID()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Instructor Not Found!"));
        les.setStudent(std);
        les.setInstructor(inst);
        les.setLessonDate(_les.getLessonDate());
//        les.setLessonTime(_les.getLessonTime());
        les.setVehicleType(_les.getVehicleType());
        lessonRepo.save(les);
        return "Saved";
    }

    //TODO: [2] Optionally, also add a DTO and method to only update the grade and feedback (maybe? or I could try to reuse it, idk)

    //Redundant code and wasted database lookup doing existsBy and then findBy, this was done to return a nice message besides 'internal server error'
    //See if there's a workaround
    //Edit: Changed to return 404 not found, if that is hard to work with will change back or further modify
    @Transactional
    public @ResponseBody String updateLesson(int ID, lessonUpdateDTO _les) {
        Lesson les = lessonRepo.findById(ID).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Lesson Not Found!"));
        Student std;
        Instructor inst;
        if (_les.getStudentID() != 0) {
            std = studentRepo.findById(_les.getStudentID()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Student Not Found!"));
            les.setStudent(std);
        }
        if (_les.getInstructorID() != 0) {
            inst = instructorRepo.findById(_les.getInstructorID()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Instructor Not Found!"));
            les.setInstructor(inst);
        }
        if (!_les.getLessonDate().toString().equals("1920-01-01"))
            les.setLessonDate(_les.getLessonDate());
        if (!_les.getVehicleType().equals("noChange"))
            les.setVehicleType(_les.getVehicleType());
        if (_les.getGrade() != 'Z')
            les.setGrade(_les.getGrade());
        if (!_les.getFeedback().equals("noChange"))
            les.setFeedback(_les.getFeedback());
        lessonRepo.save(les);
        return "Updated";
    }

    @Transactional
    public @ResponseBody String deleteLesson(int ID) {
        lessonRepo.deleteById(ID);
        return "Deleted";
    }
}
