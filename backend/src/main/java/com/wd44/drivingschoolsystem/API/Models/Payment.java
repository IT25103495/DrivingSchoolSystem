package com.wd44.drivingschoolsystem.API.Models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.wd44.drivingschoolsystem.API.Enums.vehicleTypes;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "payment")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "amount", nullable = false)
    private double amount;

    @Column(name = "paid", nullable = false)
    private boolean paid;

    @Column(name = "payment_date")
    private LocalDate paymentDate;


    @Enumerated(EnumType.ORDINAL)
    @Column(name = "vehicle_type")
    private vehicleTypes vehicleType;

    // Default constructor required by JPA
    public Payment() {}

    public Payment(double amount, boolean paid, LocalDate paymentDate, vehicleTypes vehicleType) {
        this.amount = amount;
        this.paid = paid;
        this.paymentDate = paymentDate;
        this.vehicleType = vehicleType;
    }

    //  Getters and Setters

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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
}
