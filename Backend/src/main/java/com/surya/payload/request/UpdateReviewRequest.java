package com.surya.payload.request;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UpdateReviewRequest {

    @NotNull(message = "Rating is Mandatory")
    @Min(value = 1, message = "Rating must be Alteast 1")
    @Max(value = 5, message = "Rating Must NOT Exceeed 5")
    private Integer rating;

    @NotBlank(message = "Review Text is Mandatory")
    @Size(min = 10, max = 2000, message = "Review Must be Between 10 to 2000 Characters")
    private String reviewText;

    @Size(max = 200, message = "Review Title Must NOT Exceed 200 Characters")
    private String title;

}
