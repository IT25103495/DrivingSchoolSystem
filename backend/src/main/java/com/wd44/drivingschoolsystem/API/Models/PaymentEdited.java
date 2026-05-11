package com.wd44.drivingschoolsystem.API.Models;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "payments")

public class PaymentEdited {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;

    private Double amount;
    private String cardLastFour;
    private Boolean paymentSuccess;
    private LocalDateTime paymentDate;

    public Long getId() { return id; }
    public Student getStudent() { return student; }
    public void setStudent(Student s) { this.student = s; }
    public Double getAmount() { return amount; }
    public void setAmount(Double a) { this.amount = a; }
    public String getCardLastFour() { return cardLastFour; }
    public void setCardLastFour(String c) { this.cardLastFour = c; }
    public Boolean getPaymentSuccess() { return paymentSuccess; }
    public void setPaymentSuccess(Boolean b) { this.paymentSuccess = b; }
    public LocalDateTime getPaymentDate() { return paymentDate; }
    public void setPaymentDate(LocalDateTime d) { this.paymentDate = d; }
}
