package com.surya.payload.dto;

import java.time.LocalDateTime;

import com.surya.domain.PaymentGateway;
import com.surya.domain.PaymentStatus;
import com.surya.domain.PaymentType;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PaymentDTO {

    private Long id;

    @NotNull(message = "User Id is Mandatory")
    private Long userId;

    private String userName;

    private String userEmail;

    private Long bookLoanId;

    private Long subscriptionId;

    @NotNull(message = "Payment type is Mandatory")
    private PaymentType paymentType;

    private PaymentStatus status;

    @NotNull(message = "Payment Gateway is Mandatory")
    private PaymentGateway gateway;

    @NotNull(message = "Amount is Mandatory")
    @Positive(message = "Amount must be Positive")
    private Long amount;

    private String transactionId;

    private String gatewayPaymentId;

    private String gatewayOrderId;

    private String gatewaySignature;

    private String description;

    private String failureReason;

    private Integer retryCount;

    private LocalDateTime initiatedAt;

    private LocalDateTime completedAt;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

}
