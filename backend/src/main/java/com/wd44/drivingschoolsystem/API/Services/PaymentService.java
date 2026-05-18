package com.wd44.drivingschoolsystem.API.Services;

import com.wd44.drivingschoolsystem.API.DTOs.Payment.paymentSendDTO;
import com.wd44.drivingschoolsystem.API.Enums.vehicleTypes;
import com.wd44.drivingschoolsystem.API.Models.Payment;
import com.wd44.drivingschoolsystem.API.Models.Student;
import com.wd44.drivingschoolsystem.API.Repos.PaymentRepo;
import com.wd44.drivingschoolsystem.API.Repos.StudentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashMap;
import java.util.Map;

@Service
public class PaymentService {

    @Autowired
    private StudentRepo studentRepo;

    @Autowired
    private PaymentRepo paymentRepo;

    public double calculateBill(vehicleTypes type) {
        return type == vehicleTypes.HEAVY ? 45000.0 : 30000.0;
    }

    public double calculateBill(String vehicleTypeKey) {
        vehicleTypes type = vehicleTypes.valueOf(vehicleTypeKey.trim().toUpperCase());
        return calculateBill(type);
    }

    /**
     * Validates card and amount, and persists the payment to the database.
     */
    public Map<String, Object> processPayment(paymentSendDTO payment) {
        Student student = studentRepo.findById(payment.getStudentID())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Student not found"));

        if (payment.getVehicleType() == null || payment.getVehicleType().isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Vehicle type is required");
        }

        vehicleTypes type;
        try {
            type = vehicleTypes.valueOf(payment.getVehicleType().trim().toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid vehicle type");
        }

        double expectedAmount = calculateBill(type);
        if (payment.getAmount() != expectedAmount) {
            Map<String, Object> result = new HashMap<>();
            result.put("success", false);
            result.put("message", "Payment failed. Incorrect amount.");
            return result;
        }

        String cleaned = payment.getCardNumber().replaceAll("[\\s-]", "");
        boolean success = cleaned.length() == 16 && payment.getAmount() > 0;

        if (success) {
            Payment newPayment = new Payment();
            newPayment.setStudent(student);
            newPayment.setAmount(payment.getAmount());
            newPayment.setPaymentDate(java.time.LocalDate.now());
            newPayment.setVehicleType(type);
            paymentRepo.save(newPayment);
        }

        Map<String, Object> result = new HashMap<>();
        result.put("success", success);
        result.put("message", success
                ? "Payment successful!"
                : "Payment failed. Please check your card details.");
        return result;
    }
}