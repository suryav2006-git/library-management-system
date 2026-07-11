package com.surya.payload.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CheckoutRequest {

    @NotNull(message = "Book Id is Mandatory")
    private Long bookId;

    @Min(value = 1, message = "Checkout Days must be Atleast 1")
    private Integer checkoutDate = 14;

    private String notes;

}
