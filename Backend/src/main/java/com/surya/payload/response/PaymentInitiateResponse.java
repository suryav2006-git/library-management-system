package com.surya.payload.response;

import com.surya.domain.PaymentGateway;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentInitiateResponse {

    private Long paymentId;

    private PaymentGateway gateway;

    private String transactionId;

    private String razorpayOrderId;

    private String amount;

    private String description;

    private String checkoutUrl;

    private String message;

    private Boolean success;

}
