package com.wd44.drivingschoolsystem.API.Services;

import com.wd44.drivingschoolsystem.API.DTOs.ApiResponseDTO;
import com.wd44.drivingschoolsystem.API.DTOs.Progress.ProgressCreateDTO;
import com.wd44.drivingschoolsystem.API.DTOs.Progress.ProgressResponseDTO;
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
    public ApiResponseDTO<ProgressResponseDTO> addProgress(ProgressCreateDTO dto) {

        Student student = studentRepo.findById(dto.getStudentId())
                .orElseThrow(() -> new RuntimeException("Student not found"));

        if (progressRepo.existsByStudent_ID(dto.getStudentId())) {
            return new ApiResponseDTO<>(false, "Progress already exists", null);
        }

        List<Lesson> lessons = lessonRepo.findByStudent_ID(dto.getStudentId());

        int total = lessons.size();
        int completed = (int) lessons.stream()
                .filter(l -> l.getGrade() != '\0' && l.getGrade() != 0)
                .count();

        int pending = total - completed;
        int percentage = total == 0 ? 0 : (completed * 100 / total);

        Progress progress = new Progress();
        progress.setStudent(student);
        progress.setTotalLessons(total);
        progress.setCompletedLessons(completed);
        progress.setPendingLessons(pending);
        progress.setProgressPercentage(percentage);
        progress.setLastUpdated(LocalDate.now());

        Progress saved = progressRepo.save(progress);

        return new ApiResponseDTO<>(
                true,
                "Progress created successfully",
                mapToDTO(saved)
        );
    }

    // ==================== READ ====================
    // Get progress for a specific student
    public ApiResponseDTO<ProgressResponseDTO> getProgressByStudentId(Integer studentId) {

        studentRepo.findById(studentId)
                .orElseThrow(() -> new RuntimeException("Student not found"));

        Progress progress = progressRepo.findByStudent_ID(studentId);

        if (progress == null) {
            return new ApiResponseDTO<>(false, "Progress not found", null);
        }

        return new ApiResponseDTO<>(
                true,
                "Progress fetched successfully",
                mapToDTO(progress)
        );
    }

    // Get all students progress
    public ApiResponseDTO<List<ProgressResponseDTO>> getAllStudentsProgress() {

        List<ProgressResponseDTO> list = progressRepo.findAll()
                .stream()
                .map(this::mapToDTO)
                .toList();

        return new ApiResponseDTO<>(
                true,
                "All progress records fetched",
                list
        );
    }

    // ==================== UPDATE ====================
    // Recalculates and updates progress from lessons
    @Transactional
    public ApiResponseDTO<ProgressResponseDTO> updateProgress(ProgressUpdateDTO dto) {

        Student student = studentRepo.findById(dto.getStudentId())
                .orElseThrow(() -> new RuntimeException("Student not found"));

        Progress progress = progressRepo.findByStudent_ID(dto.getStudentId());

        if (progress == null) {
            return new ApiResponseDTO<>(false, "Progress not found", null);
        }

        List<Lesson> lessons = lessonRepo.findByStudent_ID(dto.getStudentId());

        int total = lessons.size();
        int completed = (int) lessons.stream()
                .filter(l -> l.getGrade() != '\0' && l.getGrade() != 0)
                .count();

        int pending = total - completed;
        int percentage = total == 0 ? 0 : (completed * 100 / total);

        progress.setTotalLessons(total);
        progress.setCompletedLessons(completed);
        progress.setPendingLessons(pending);
        progress.setProgressPercentage(percentage);
        progress.setLastUpdated(LocalDate.now());

        Progress updated = progressRepo.save(progress);

        return new ApiResponseDTO<>(
                true,
                "Progress updated successfully",
                mapToDTO(updated)
        );
    }

    // ==================== DELETE ====================
    // Delete a progress record
    @Transactional
    public ApiResponseDTO<String> deleteProgress(Integer id) {

        if (!progressRepo.existsById(id)) {
            return new ApiResponseDTO<>(false, "Progress not found", null);
        }

        progressRepo.deleteById(id);

        return new ApiResponseDTO<>(
                true,
                "Progress deleted successfully",
                "Deleted ID: " + id
        );
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


    private ProgressResponseDTO mapToDTO(Progress progress) {

        ProgressResponseDTO dto = new ProgressResponseDTO();

        dto.setProgressId(progress.getProgressID());
        dto.setStudentId(progress.getStudent().getID());

        dto.setTotalLessons(progress.getTotalLessons());
        dto.setCompletedLessons(progress.getCompletedLessons());
        dto.setPendingLessons(progress.getPendingLessons());
        dto.setProgressPercentage(progress.getProgressPercentage());

        dto.setLastUpdated(progress.getLastUpdated().toString());

        return dto;
    }
}
