package com.wd44.drivingschoolsystem.Controllers;

import com.wd44.drivingschoolsystem.DTOs.Lesson.lessonCreateDTO;
//import com.wd44.drivingschoolsystem.DTOs.Lesson.lessonUpdateDTO;
import com.wd44.drivingschoolsystem.Models.Lesson;
import com.wd44.drivingschoolsystem.Repos.LessonRepo;
import com.wd44.drivingschoolsystem.Services.LessonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@Controller
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

//    @PutMapping(path="/update")
//    public @ResponseBody String updateLesson(@RequestParam int ID, @RequestBody lessonUpdateDTO les) {
//        return lessonService.updateLesson(ID, les);
//    }

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
}