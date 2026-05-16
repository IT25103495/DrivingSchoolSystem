package com.wd44.drivingschoolsystem.API.DTOs.Lesson;

import java.time.LocalDate;

public class lessonAutoRegisterDTO {
    private String vehicleType;

    private LocalDate firstDate;

    private String username;

    public LocalDate getFirstDate() {
        return firstDate;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public String getUsername() {
        return username;
    }
}