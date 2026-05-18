package com.wd44.drivingschoolsystem.API.Repos;

import com.wd44.drivingschoolsystem.API.Models.Lesson;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

public interface LessonRepo extends CrudRepository<Lesson, Integer> {
    Iterable<Lesson> findByStudent_authEntity_Username_OrderByLessonDate(String username);
    Iterable<Lesson> findByInstructor_authEntity_Username_OrderByLessonDate(String username);

    List<Lesson> findByStudent_ID(Integer id);
}