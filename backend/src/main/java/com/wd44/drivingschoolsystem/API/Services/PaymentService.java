package com.wd44.drivingschoolsystem.API.Services;

import com.wd44.drivingschoolsystem.API.Enums.vehicleTypes;
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

    public double calculateBill(vehicleTypes type) {
        return type == vehicleTypes.HEAVY ? 45000.0 : 30000.0;
    }

    public double calculateBill(String vehicleTypeKey) {
        vehicleTypes type = vehicleTypes.valueOf(vehicleTypeKey.trim().toUpperCase());
        return calculateBill(type);
    }

    /**
     * Validates card and amount only — nothing is persisted (no payment table).
     */
    public Map<String, Object> processPayment(Integer studentId, String cardNumber, double amount) {
        if (!studentRepo.existsById(studentId)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Student not found");
        }

        String cleaned = cardNumber.replaceAll("[\\s-]", "");
        boolean success = cleaned.length() == 16 && amount > 0;

        Map<String, Object> result = new HashMap<>();
        result.put("success", success);
        result.put("message", success
                ? "Payment successful! You can now log in."
                : "Payment failed. Please check your card details.");
        return result;
    }
}