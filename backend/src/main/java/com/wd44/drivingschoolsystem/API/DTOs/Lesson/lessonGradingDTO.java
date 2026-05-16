package com.wd44.drivingschoolsystem.API.DTOs.Lesson;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Id;

import java.time.LocalDate;

public class lessonGradingDTO {
    @Id
    private int id;

    @Schema(defaultValue = "Z")
    private char grade = 'Z';

    @Schema(defaultValue = "noChange")
    private String feedback = "noChange";

    public int getId() {return id;}
    public char getGrade() {
        return grade;
    }
    public String getFeedback() {
        return feedback;
    }
}