package com.wd44.drivingschoolsystem.API.Repos;

import com.wd44.drivingschoolsystem.API.Models.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Integer> {

    List<Payment> findByPaid(boolean paid); // Finds all payments that have either been paid or not paid
}
