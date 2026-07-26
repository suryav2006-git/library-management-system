package com.surya.payload.request;

import com.surya.domain.FineType;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateFineRequest {

    @NotNull(message = "Book Loan ID is Mandatory")
    private Long bookLoanId;

    @NotNull(message = "Fine Type is Mandatory")
    private FineType type;

    @NotNull(message = "Fine Amount is Mandatory")
    @Positive(message = "Fine Amount Must be Positive")
    private Long amount;

    private String reason;

    private String notes;

}
