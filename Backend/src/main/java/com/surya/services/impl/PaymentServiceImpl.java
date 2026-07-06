package com.surya.services.impl;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.surya.domain.PaymentStatus;
import com.surya.modal.Payment;
import com.surya.modal.Subscription;
import com.surya.modal.User;
import com.surya.payload.dto.PaymentDTO;
import com.surya.payload.request.PaymentInitiateRequest;
import com.surya.payload.request.PaymentVerifyRequest;
import com.surya.payload.response.PaymentInitiateResponse;
import com.surya.repository.PaymentRepository;
import com.surya.repository.SubscriptionRepository;
import com.surya.repository.UserRepository;
import com.surya.services.PaymentService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {

    private final UserRepository userRepository;
    private final SubscriptionRepository subscriptionRepository;
    private final PaymentRepository paymentRepository;

    @Override
    public PaymentInitiateResponse initiatePayment(PaymentInitiateRequest request) throws Exception {

        User user = userRepository.findById(request.getUserId()).get();

        Payment payment = new Payment();

        payment.setUser(user);
        payment.setPaymentType(request.getPaymentType());
        payment.setGateway(request.getPaymentGateway());
        payment.setAmount(request.getAmount());
        payment.setDescription(request.getDescription());
        payment.setStatus(PaymentStatus.PENDING);
        payment.setTransactionId("TXN_" + UUID.randomUUID());
        payment.setInitiatedAt(LocalDateTime.now());

        if (request.getSubscriptionId() != null) {
            Subscription sub = subscriptionRepository
                    .findById(request.getSubscriptionId())
                    .orElseThrow(
                            () -> new Exception("Subscription not Found"));
            payment.setSubscription(sub);

        }
        payment = paymentRepository.save(payment);

        return null;
    }

    @Override
    public PaymentDTO verifyPayment(PaymentVerifyRequest req) {
        return null;
    }

    @Override
    public Page<PaymentDTO> getAllPayments(Pageable pageable) {
        return null;
    }

}
