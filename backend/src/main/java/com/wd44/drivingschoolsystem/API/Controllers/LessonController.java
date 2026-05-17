package com.wd44.drivingschoolsystem.API.Controllers;

import com.wd44.drivingschoolsystem.API.DTOs.Lesson.lessonAutoRegisterDTO;
import com.wd44.drivingschoolsystem.API.DTOs.Lesson.lessonCreateDTO;
//import com.wd44.drivingschoolsystem.DTOs.Lesson.lessonUpdateDTO;
import com.wd44.drivingschoolsystem.API.DTOs.Lesson.lessonGradingDTO;
import com.wd44.drivingschoolsystem.API.DTOs.Lesson.lessonUpdateDTO;
import com.wd44.drivingschoolsystem.API.Models.Lesson;
import com.wd44.drivingschoolsystem.API.Repos.LessonRepo;
import com.wd44.drivingschoolsystem.API.Services.LessonService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@Controller
@CrossOrigin
@SecurityRequirement(name = "bearerAuth")
@RequestMapping(path="/lesson")
public class LessonController {
    @Autowired
    private LessonRepo lessonRepo;

    @Autowired
    private LessonService lessonService;

    @PostMapping(path="/add") // Map ONLY POST Requests
    public @ResponseBody String addNewLesson(@RequestBody lessonCreateDTO les) {
        return lessonService.addNewLesson(les);
    }

    @PostMapping(path="/autoRegister") // Map ONLY POST Requests
    public @ResponseBody String autoRegister(@RequestBody lessonAutoRegisterDTO les) {
        return lessonService.autoRegister(les);
    }

    @PutMapping(path="/update")
    public @ResponseBody String updateLesson(@RequestParam int ID, @RequestBody lessonUpdateDTO les) {
        return lessonService.updateLesson(ID, les);
    }

    @DeleteMapping(path="/delete")
    public @ResponseBody String deleteLesson(@RequestParam int ID) {
        return lessonService.deleteLesson(ID);
    }

    @GetMapping(path="/getAll")
    public @ResponseBody Iterable<Lesson> getAllLessons() {
        return lessonRepo.findAll();
    }

    //use this as reference if I need better response bodies/codes later
    @GetMapping(path="/getById")
    public @ResponseBody ResponseEntity<Lesson> getLessonByID(@RequestParam int ID) {
        return lessonRepo.findById(ID)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping(path="/getByUser")
    public @ResponseBody Iterable<Lesson> getLessonsByUser(@RequestParam String user) {
        return lessonRepo.findByStudent_authEntity_Username_OrderByLessonDate(user);
    }

    @GetMapping(path="/getByInstructor")
    public @ResponseBody Iterable<Lesson> getLessonsByInstructor(@RequestParam String user) {
        return lessonRepo.findByInstructor_authEntity_Username_OrderByLessonDate(user);
    }

    @PutMapping(path="/grade")
    public @ResponseBody String gradeLesson(@RequestBody lessonGradingDTO les) {
        return lessonService.gradeLesson(les);
    }

}