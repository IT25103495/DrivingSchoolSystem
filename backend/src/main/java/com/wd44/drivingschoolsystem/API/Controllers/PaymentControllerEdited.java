package com.wd44.drivingschoolsystem.API.Controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@RestController
@RequestMapping("/api/payment")
@CrossOrigin(origins = "*")
public class PaymentControllerEdited {

    @Autowired private com.wd44.drivingschoolsystem.service.PaymentServiceEdited paymentService;

    // GET /api/payment/calculate/light  → 30000.0
    // GET /api/payment/calculate/heavy  → 45000.0
    @GetMapping("/calculate/{classType}")
    public ResponseEntity<Double> calculate(@PathVariable String classType) {
        return ResponseEntity.ok(paymentService.calculateBill(classType));
    }

    // POST /api/payment/process
    // Body: { "studentId":1, "cardNumber":"1234567890123456", "amount":30000 }
    // Returns: { "success": true, "message": "Payment successful!..." }
    @PostMapping("/process")
    public ResponseEntity<Map<String, Object>> process(
            @RequestBody Map<String, Object> body) {
        Long studentId    = Long.valueOf(body.get("studentId").toString());
        String cardNumber = body.get("cardNumber").toString();
        Double amount     = Double.valueOf(body.get("amount").toString());
        return ResponseEntity.ok(
                paymentService.processPayment(studentId, cardNumber, amount));
    }
}
