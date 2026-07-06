package com.surya.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.surya.modal.Payment;

public interface PaymentRepository extends JpaRepository<Payment, Long> {

}
