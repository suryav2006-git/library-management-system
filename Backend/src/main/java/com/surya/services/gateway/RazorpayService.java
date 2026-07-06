package com.surya.services.gateway;

import org.springframework.stereotype.Service;

import com.razorpay.RazorpayClient;
import com.surya.modal.Payment;
import com.surya.modal.User;
import com.surya.payload.response.PaymentLinkResponse;

import lombok.RequiredArgsConstructor;
import lombok.Value;

@Service
@RequiredArgsConstructor
public class RazorpayService {

    public PaymentLinkResponse createPaymentLink(User user, Payment payment) {

        @Value("${razorpay.key.id:}")
        private String razorpayKeyId;

        @Value("${razorpay.key.secret:}")
        private String razorpayKeySecret;


        try {
            RazorpayClient razorpayClient = new RazorpayClient(null, null)
        } catch {
            return null ;
        }


        return null;
    }

}
