package com.surya.services.impl;

import java.time.LocalDateTime;
import java.util.UUID;

import org.json.JSONObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.surya.domain.PaymentGateway;
import com.surya.domain.PaymentStatus;
import com.surya.event.publisher.PaymentEventPublisher;
import com.surya.mapper.PaymentMapper;
import com.surya.modal.Payment;
import com.surya.modal.Subscription;
import com.surya.modal.User;
import com.surya.payload.dto.PaymentDTO;
import com.surya.payload.request.PaymentInitiateRequest;
import com.surya.payload.request.PaymentVerifyRequest;
import com.surya.payload.response.PaymentInitiateResponse;
import com.surya.payload.response.PaymentLinkResponse;
import com.surya.repository.PaymentRepository;
import com.surya.repository.SubscriptionRepository;
import com.surya.repository.UserRepository;
import com.surya.services.PaymentService;
import com.surya.services.gateway.RazorpayService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {

    private final UserRepository userRepository;
    private final SubscriptionRepository subscriptionRepository;
    private final PaymentRepository paymentRepository;
    private final RazorpayService razorpayService;
    private final PaymentMapper paymentMapper;
    private final PaymentEventPublisher paymentEventPublisher;

    @Override
    public PaymentInitiateResponse initiatePayment(PaymentInitiateRequest request) throws Exception {

        User user = userRepository.findById(request.getUserId()).get();

        Payment payment = new Payment();

        payment.setUser(user);
        payment.setPaymentType(request.getPaymentType());
        payment.setGateway(request.getGateway());
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
        PaymentInitiateResponse response = new PaymentInitiateResponse();

        if (request.getGateway() == PaymentGateway.RAZORPAY) {
            PaymentLinkResponse paymentLinkResponse = razorpayService.createPaymentLink(
                    user, payment);
            response = PaymentInitiateResponse.builder()
                    .paymentId(payment.getId())
                    .gateway(payment.getGateway())
                    .checkoutUrl(paymentLinkResponse.getPayment_link_url())
                    .transactionId(paymentLinkResponse.getPayment_link_id())
                    .amount(payment.getAmount())
                    .description(payment.getDescription())
                    .success(true)
                    .message("Payment Initiated Successfully")
                    .build();

            payment.setGatewayOrderId(paymentLinkResponse.getPayment_link_id());
        }

        payment.setStatus(PaymentStatus.PROCESSING);
        paymentRepository.save(payment);

        // payment initiate event

        return response;
    }

    @Override
    public PaymentDTO verifyPayment(PaymentVerifyRequest req) throws Exception {

        JSONObject paymentDetails = razorpayService.fetchPaymentDetails(
                req.getRazorpayPaymentId());

        JSONObject notes = paymentDetails.getJSONObject("notes");

        Long paymentId = Long.parseLong(notes.optString("payment_id"));

        Payment payment = paymentRepository.findById(paymentId).get();

        boolean isValid = razorpayService.isValidPayment(req.getRazorpayPaymentId());

        if (PaymentGateway.RAZORPAY == payment.getGateway()) {
            if (isValid) {
                payment.setGatewayOrderId(req.getRazorpayPaymentId());
            }
        }
        if (isValid) {
            payment.setStatus(PaymentStatus.SUCCESS);
            payment.setCompletedAt(LocalDateTime.now());
            payment = paymentRepository.save(payment);

            paymentEventPublisher.publishPaymentSuccessEvent(payment);
        }

        return paymentMapper.toDTO(payment);
    }

    @Override
    public Page<PaymentDTO> getAllPayments(Pageable pageable) {
        Page<Payment> payments = paymentRepository.findAll(pageable);

        return payments.map(paymentMapper::toDTO);
    }

}
