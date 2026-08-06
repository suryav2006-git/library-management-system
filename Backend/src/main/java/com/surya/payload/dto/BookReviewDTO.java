package com.surya.payload.dto;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class BookReviewDTO {

    private Long id;

    @NotNull(message = "User ID is Mandatory")
    private Long userId;

    private String userName;

    @NotNull(message = "Book ID is Mandatory")
    private Long bookId;

    private String bookTitle;

    @NotNull(message = "Rating is Mandatory")
    @Min(value = 1, message = "Rating Must Be Atleast 1")
    @Max(value = 5, message = "Rating Must Not Exceed 5")
    private Integer rating;

    @NotBlank(message = "Review Text is Mandatory")
    @Size(min = 10, max = 2000, message = "Review must be Between 10 and 2000 Chracters")
    private String reviewText;

    private Boolean isVerifiedReader;

    private Boolean isActive;

    private Integer helpfulCount;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createdAt;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updatedAt;

}
