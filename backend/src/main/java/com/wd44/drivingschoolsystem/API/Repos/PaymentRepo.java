package com.wd44.drivingschoolsystem.API.Repos;

import com.wd44.drivingschoolsystem.API.Models.Payment;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentRepo extends CrudRepository<Payment, Integer> {

}
