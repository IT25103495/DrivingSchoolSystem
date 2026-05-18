package com.wd44.drivingschoolsystem.API.Models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.wd44.drivingschoolsystem.API.Enums.vehicleTypes;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer ID;

    @Column(name = "Amount", nullable = false)
    private double amount;

    @Column(name = "Payment Date")
    private LocalDate paymentDate;

    @Column(name = "Vehicle Type")
    private vehicleTypes vehicleType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id", nullable = false)
    private Student student;

    //  Getters and Setters

    public Integer getId() {
        return ID;
    }
    public void setId(Integer ID) {
        this.ID = ID;
    }

    public double getAmount() {
        return amount;
    }
    public void setAmount(double amount) {
        this.amount = amount;
    }

    public LocalDate getPaymentDate() {
        return paymentDate;
    }
    public void setPaymentDate(LocalDate paymentDate) {
        this.paymentDate = paymentDate;
    }

    public vehicleTypes getVehicleType() {
        return vehicleType;
    }
    public void setVehicleType(vehicleTypes vehicleType) {
        this.vehicleType = vehicleType;
    }

    public Student getStudent() {
        return student;
    }
    public void setStudent(Student student) {
        this.student = student;
    }
}
