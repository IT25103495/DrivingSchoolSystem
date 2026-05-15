package com.wd44.drivingschoolsystem.API.Services;

import com.wd44.drivingschoolsystem.API.Models.Lesson;
import com.wd44.drivingschoolsystem.API.Models.Student;
import com.wd44.drivingschoolsystem.API.Repos.LessonRepo;
import com.wd44.drivingschoolsystem.API.Repos.StudentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ProgressService {

    @Autowired
    private LessonRepo lessonRepo;

    @Autowired
    private StudentRepo studentRepo;

    // Total lessons required to complete the course
    private static final int TOTAL_COURSE_LESSONS = 10;

    public Map<String, Object> getProgressByStudentId(Integer studentId) {
        // Verify student exists
        Student student = studentRepo.findById(studentId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Student Not Found!"));

        // Get all lessons for the student
        List<Lesson> allLessons = new ArrayList<>();
        lessonRepo.findAll().forEach(lesson -> {
            if (lesson.getStudent() != null &&
                lesson.getStudent().getID().equals(studentId)) {
                allLessons.add(lesson);
            }
        });

        // Build chart data from lessons that have grades
        List<Map<String, Object>> chartData = new ArrayList<>();
        int lessonNumber = 1;
        for (Lesson lesson : allLessons) {
            Map<String, Object> point = new HashMap<>();
            point.put("lessonNumber", lessonNumber);
            point.put("lessonID", lesson.getLessonID());
            point.put("grade", String.valueOf(lesson.getGrade()));
            point.put("feedback", lesson.getFeedback());
            point.put("lessonDate", lesson.getLessonDate() != null ? lesson.getLessonDate().toString() : null);
            point.put("vehicleType", lesson.getVehicleType());
            chartData.add(point);
            lessonNumber++;
        }

        // Count completed lessons (those with a non-null, non-default grade)
        long completedLessons = allLessons.stream()
                .filter(l -> l.getGrade() != '\0' && l.getGrade() != 0)
                .count();

        long pendingLessons = TOTAL_COURSE_LESSONS - completedLessons;
        if (pendingLessons < 0) pendingLessons = 0;

        // Calculate percentage
        double progressPercentage = ((double) completedLessons / TOTAL_COURSE_LESSONS) * 100;

        // Build result map
        Map<String, Object> result = new HashMap<>();
        result.put("studentId", studentId);
        result.put("studentName", student.getFullName());
        result.put("totalLessons", TOTAL_COURSE_LESSONS);
        result.put("completedLessons", completedLessons);
        result.put("pendingLessons", pendingLessons);
        result.put("progressPercentage", Math.round(progressPercentage));
        result.put("chartData", chartData);

        return result;
    }

    public List<Map<String, Object>> getAllStudentsProgress() {
        List<Map<String, Object>> allProgress = new ArrayList<>();

        studentRepo.findAll().forEach(student -> {
            List<Lesson> lessons = new ArrayList<>();
            lessonRepo.findAll().forEach(lesson -> {
                if (lesson.getStudent() != null &&
                    lesson.getStudent().getID().equals(student.getID())) {
                    lessons.add(lesson);
                }
            });

            long completed = lessons.stream()
                    .filter(l -> l.getGrade() != '\0' && l.getGrade() != 0)
                    .count();

            double percentage = ((double) completed / TOTAL_COURSE_LESSONS) * 100;

            Map<String, Object> studentProgress = new HashMap<>();
            studentProgress.put("studentId", student.getID());
            studentProgress.put("studentName", student.getFullName());
            studentProgress.put("completedLessons", completed);
            studentProgress.put("totalLessons", TOTAL_COURSE_LESSONS);
            studentProgress.put("progressPercentage", Math.round(percentage));

            allProgress.add(studentProgress);
        });

        return allProgress;
    }
}
