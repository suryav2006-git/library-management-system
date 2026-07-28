package com.surya.payload.dto;

import java.time.LocalDateTime;

import com.surya.domain.FineStatus;
import com.surya.domain.FineType;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FineDTO {

    private Long id;

    @NotNull(message = "Book Loan ID is Mandatory")
    private Long bookLoanId;

    private String bookTitle;

    private String bookIsbn;

    @NotNull(message = "User ID is Mandatory")
    private Long userId;

    private String userName;

    private String userEmail;

    @NotNull(message = "Fine Type is Mandatory")
    private FineType type;

    @NotNull(message = "Fine Amount is Mandatory")
    @PositiveOrZero(message = "Fine Amount Can't be Negative")
    private Long amount;

    @PositiveOrZero(message = "Amount Paid Cannot Be Negative")
    private Long amountPaid;

    private Long amountOutStanding;

    @NotNull(message = "Fine Status is Mandatory")
    private FineStatus status;

    private String reason;

    private String notes;

    private Long waivedByUserId;

    private String waivedByUserName;

    private LocalDateTime waivedAt;

    private String waiverReason;

    private LocalDateTime paidAt;

    private Long processedByUserId;

    private String processedByUserName;

    private String transactionId;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

}
