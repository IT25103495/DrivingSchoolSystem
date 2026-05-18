package com.wd44.drivingschoolsystem.API.DTOs.Payment;

public class paymentSendDTO {
    protected int studentID;
    protected String cardNumber;
    protected double amount;
    protected String vehicleType;

    //getters

    public int getStudentID() {
        return studentID;
    }
    public String getCardNumber() {
        return cardNumber;
    }
    public double getAmount() {
        return amount;
    }
    public String getVehicleType() {
        return vehicleType;
    }
}
