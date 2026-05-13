package com.wd44.drivingschoolsystem.API.DTOs.Payment;

public class paymentSendDTO {
    protected int studentID;
    protected String cardNumber;
    protected double amount;

    public int getStudentID() {
        return studentID;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public double getAmount() {
        return amount;
    }
}
