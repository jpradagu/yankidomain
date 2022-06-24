package com.nttdata.bootcamp.yankidomain.service;

import com.nttdata.bootcamp.yankidomain.model.Payment;
import com.nttdata.bootcamp.yankidomain.repository.PaymentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * PaymentService.
 */
@Service
public class PaymentService {

  private final Logger logger = LoggerFactory.getLogger(PaymentService.class);
  @Autowired
  private PaymentRepository paymentRepository;


  /**
   * findAll Payment.
   */
  public Flux<Payment> findAll() {
    return paymentRepository.findAll();
  }

  /**
   * findById Payment.
   */
  public Mono<Payment> findById(String id) {
    return paymentRepository.findById(id);
  }

  /**
   * create payment.
   */
  public Mono<Payment> create(Payment payment) {
    return paymentRepository.save(payment);
  }

  /**
   * delete CustomerDebitCard.
   */
  public Mono<Void> delete(Payment payment) {
    return paymentRepository.delete(payment);
  }

  /**
   * paymentWallet BrokerMessage.
   */
  @KafkaListener(topics = "payment_wallet", groupId = "group_id")
  public void paymentWallet(Payment payment) {
    logger.info("receiver: ->" + payment.toString());
    paymentRepository.save(payment).flatMap(p -> {
      logger.info(p.toString());
      return Mono.just(p);
    }).subscribe();
  }
}
