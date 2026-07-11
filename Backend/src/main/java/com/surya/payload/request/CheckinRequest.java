package com.surya.payload.request;

import com.surya.domain.BookLoanStatus;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CheckinRequest {

    @NotNull(message = "Book Loan Id is Mandatory")
    private Long bookLoanId;

    private BookLoanStatus condition = BookLoanStatus.RETURNED;

    private String notes;

}
