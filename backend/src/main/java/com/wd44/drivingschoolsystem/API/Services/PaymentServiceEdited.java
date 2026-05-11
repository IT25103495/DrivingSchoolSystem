package com.wd44.drivingschoolsystem.API.Services;

import com.drivingschool.driving_school.model.Student;
import com.drivingschool.driving_school.repository.PaymentRepository;
import com.drivingschool.driving_school.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.Map;

@Service
public class PaymentServiceEdited {

    @Autowired private PaymentRepository paymentRepository;
    @Autowired private StudentRepository studentRepository;

    public Double calculateBill(String classType) {
        return classType != null && classType.toLowerCase().contains("heavy") ? 45000.0 : 30000.0;
    }

    // Process payment — called right after registration
    public Map<String, Object> processPayment(
            Long studentId, String cardNumber, Double amount) {

        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new RuntimeException("Student not found"));

        // Validate card: 16 digits, amount > 0
        String cleaned = cardNumber.replaceAll("\\s|-", "");
        boolean success = cleaned.length() == 16 && amount > 0;

        com.drivingschool.driving_school.model.PaymentEdited p = new com.drivingschool.driving_school.model.PaymentEdited();
        p.setStudent(student);
        p.setAmount(amount);
        p.setCardLastFour(cleaned.substring(cleaned.length() - 4));
        p.setPaymentSuccess(success);
        p.setPaymentDate(LocalDateTime.now());
        paymentRepository.save(p);

        Map<String, Object> result = new java.util.HashMap<>();
        result.put("success", success);
        result.put("message", success
                ? "Payment successful! You can now log in."
                : "Payment failed. Please check your card details.");
        return result;
    }
}
