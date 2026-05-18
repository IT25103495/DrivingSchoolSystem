package com.wd44.drivingschoolsystem.API.Services;

import com.wd44.drivingschoolsystem.API.DTOs.Progress.ProgressCreateDTO;
import com.wd44.drivingschoolsystem.API.DTOs.Progress.ProgressUpdateDTO;
import com.wd44.drivingschoolsystem.API.Models.Lesson;
import com.wd44.drivingschoolsystem.API.Models.Progress;
import com.wd44.drivingschoolsystem.API.Models.Student;
import com.wd44.drivingschoolsystem.API.Repos.LessonRepo;
import com.wd44.drivingschoolsystem.API.Repos.ProgressRepo;
import com.wd44.drivingschoolsystem.API.Repos.StudentRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class ProgressService {

    @Autowired
    private ProgressRepo progressRepo;

    @Autowired
    private StudentRepo studentRepo;

    @Autowired
    private LessonRepo lessonRepo;

    private static final int TOTAL_COURSE_LESSONS = 10;

    // ==================== CREATE ====================
    // Creates a progress record for a student
    @Transactional
    public @ResponseBody String addProgress(ProgressCreateDTO _progress) {
        Student student = studentRepo.findById(_progress.getStudentId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Student Not Found!"));

        // Check if progress already exists for this student
        if (progressRepo.existsByStudent_ID(_progress.getStudentId())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Progress record already exists for this student!");
        }

        // Calculate progress from lessons
        List<Lesson> lessons = getLessonsForStudent(_progress.getStudentId());
        long completed = countCompletedLessons(lessons);
        long pending = Math.max(TOTAL_COURSE_LESSONS - completed, 0);
        int percentage = (int) Math.round(((double) completed / TOTAL_COURSE_LESSONS) * 100);

        Progress progress = new Progress();
        progress.setStudent(student);
        progress.setTotalLessons(TOTAL_COURSE_LESSONS);
        progress.setCompletedLessons((int) completed);
        progress.setPendingLessons((int) pending);
        progress.setProgressPercentage(percentage);
        progress.setLastUpdated(LocalDate.now());

        progressRepo.save(progress);
        return "Saved";
    }

    // ==================== READ ====================
    // Get progress for a specific student
    public Progress getProgressByStudentId(Integer studentId) {
        studentRepo.findById(studentId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Student Not Found!"));

        Progress progress = progressRepo.findByStudent_ID(studentId);
        if (progress == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Progress record not found for this student!");
        }
        return progress;
    }

    // Get all students progress
    public Iterable<Progress> getAllStudentsProgress() {
        return progressRepo.findAll();
    }

    // ==================== UPDATE ====================
    // Recalculates and updates progress from lessons
    @Transactional
    public @ResponseBody String updateProgress(int ID, ProgressUpdateDTO _progress) {
        Progress progress = progressRepo.findById(ID)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Progress Not Found!"));

        // Recalculate from lessons
        List<Lesson> lessons = getLessonsForStudent(progress.getStudent().getID());
        long completed = countCompletedLessons(lessons);
        long pending = Math.max(TOTAL_COURSE_LESSONS - completed, 0);
        int percentage = (int) Math.round(((double) completed / TOTAL_COURSE_LESSONS) * 100);

        progress.setCompletedLessons((int) completed);
        progress.setPendingLessons((int) pending);
        progress.setProgressPercentage(percentage);
        progress.setLastUpdated(LocalDate.now());

        progressRepo.save(progress);
        return "Updated";
    }

    // ==================== DELETE ====================
    // Delete a progress record
    @Transactional
    public @ResponseBody String deleteProgress(int ID) {
        if (progressRepo.existsById(ID)) {
            progressRepo.deleteById(ID);
            return "Deleted";
        } else {
            return "Not Found";
        }
    }

    // ==================== HELPERS ====================
    private List<Lesson> getLessonsForStudent(Integer studentId) {
        List<Lesson> lessons = new ArrayList<>();
        lessonRepo.findAll().forEach(lesson -> {
            if (lesson.getStudent() != null &&
                    lesson.getStudent().getID().equals(studentId)) {
                lessons.add(lesson);
            }
        });
        return lessons;
    }

    private long countCompletedLessons(List<Lesson> lessons) {
        return lessons.stream()
                .filter(l -> l.getGrade() != '\0' && l.getGrade() != 0)
                .count();
    }
}