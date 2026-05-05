package com.wd44.drivingschoolsystem.Models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.wd44.drivingschoolsystem.Enums.vehicleTypes;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.LocalDate;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Entity
public class Payment {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    protected Integer ID;

    protected vehicleTypes vehicleType;

    protected double amount;

    protected LocalDate paymentDate;

    protected boolean paid;


    public Integer getID() {
        return ID;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }

    public String getVehicleType() {
        return vehicleType.toString();
    }

    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleTypes.valueOf(vehicleType.toUpperCase());
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

    public boolean isPaid() {
        return paid;
    }

    public void setPaid(boolean paid) {
        this.paid = paid;
    }
}
