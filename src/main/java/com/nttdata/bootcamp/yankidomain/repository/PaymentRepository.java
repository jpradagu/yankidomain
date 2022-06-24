package com.nttdata.bootcamp.yankidomain.repository;

import com.nttdata.bootcamp.yankidomain.model.Payment;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

/**
 * PaymentRepository.
 */
public interface PaymentRepository extends ReactiveMongoRepository<Payment, String> {
}
