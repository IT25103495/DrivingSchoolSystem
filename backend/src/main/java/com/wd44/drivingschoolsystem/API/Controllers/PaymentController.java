package com.wd44.drivingschoolsystem.API.Controllers;

import com.wd44.drivingschoolsystem.API.Services.PaymentService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
@CrossOrigin
@SecurityRequirement(name = "bearerAuth")
@RequestMapping(path = "/payment")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @GetMapping(path = "/calculate/{vehicleType}")
    public @ResponseBody ResponseEntity<Double> calculate(@PathVariable String vehicleType) {
        return ResponseEntity.ok(paymentService.calculateBill(vehicleType));
    }

    @PostMapping(path = "/process")
    public @ResponseBody ResponseEntity<Map<String, Object>> process(@RequestBody Map<String, Object> body) {
        Integer studentId = Integer.valueOf(body.get("studentId").toString());
        String cardNumber = body.get("cardNumber").toString();
        double amount = Double.parseDouble(body.get("amount").toString());
        return ResponseEntity.ok(paymentService.processPayment(studentId, cardNumber, amount));
    }
}