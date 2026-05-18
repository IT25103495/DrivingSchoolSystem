package com.wd44.drivingschoolsystem.API.Repos;

import com.wd44.drivingschoolsystem.API.Models.Progress;
import org.springframework.data.repository.CrudRepository;

// This will be AUTO IMPLEMENTED by Spring into a Bean called progressRepository
// CRUD refers Create, Read, Update, Delete

public interface ProgressRepo extends CrudRepository<Progress, Integer> {
    Progress findByStudent_ID(Integer studentId);
    boolean existsByStudent_ID(Integer studentId);
}
