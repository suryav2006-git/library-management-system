package com.surya.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.surya.payload.dto.PaymentDTO;
import com.surya.payload.request.PaymentInitiateRequest;
import com.surya.payload.request.PaymentVerifyRequest;
import com.surya.payload.response.PaymentInitiateResponse;

public interface PaymentService {

    PaymentInitiateResponse initiatePayment(PaymentInitiateRequest req) throws Exception;

    PaymentDTO verifyPayment(PaymentVerifyRequest req);

    Page<PaymentDTO> getAllPayments(Pageable pageable);

}
