package com.wd44.drivingschoolsystem.Repos;

import com.wd44.drivingschoolsystem.Classes.Student;
import org.springframework.data.repository.CrudRepository;

// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

public interface StudentRepository extends CrudRepository<Student, Integer> {

}