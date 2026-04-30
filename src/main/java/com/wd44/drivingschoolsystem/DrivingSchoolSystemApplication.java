package com.wd44.drivingschoolsystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DrivingSchoolSystemApplication {

    static void main(String[] args) {
        SpringApplication.run(DrivingSchoolSystemApplication.class, args);
        System.out.println("\u001B[1;96m" + "--And we're live!--" + "\u001B[0;37m");
    }

}
