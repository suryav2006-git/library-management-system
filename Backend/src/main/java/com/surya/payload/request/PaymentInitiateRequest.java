package com.surya.payload.request;

import com.surya.domain.PaymentGateway;
import com.surya.domain.PaymentType;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PaymentInitiateRequest {

    @NotNull(message = "User Id is Mandatory")
    private Long userId;

    private Long bookLoanId;

    @NotNull(message = "Payment type is Mandatory")
    private PaymentType paymentType;

    @NotNull(message = "Payment Gateway is Mandatory")
    private PaymentGateway gateway;

    @NotNull(message = "Amount is Mandatory")
    @Positive(message = "Amount must be Positive")
    private Long amount;

    @Size(max = 500, message = "Description must NOT exceed 500 characters")
    private String description;

    private Long fineId;
    private Long subscriptionId;

    @Size(max = 500, message = "Success Url must Not exceed 500 characters")
    private String successUrl;

    @Size(max = 500, message = "Cancel Url must Not exceed 500 characters")
    private String cancelUrl;

}
