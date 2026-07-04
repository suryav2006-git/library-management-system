package com.surya.payload.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
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
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class BookDTO {

    private Long id;

    @NotBlank(message = "ISBN is Mandatory")
    private String isbn;

    @NotBlank(message = "Title is Mandatory")
    @Size(min = 1, max = 255, message = "Title must be between 1 and 255 characters")
    private String title;

    @NotBlank(message = "Author is Mandatory")
    @Size(min = 1, max = 255, message = "Author must be between 1 and 255 characters")
    private String author;
    
    @NotNull(message = "Genre is Mandatory")
    private Long genreId;

    private String genreName;

    private String genreCode;

    @Size(min = 1, max = 100, message = "Publisher must be between 1 and 100 characters")
    private String publisher;

    private LocalDate publishedDate;

    @Size(max = 20, message = "Language must not exceed 20 characters")
    private String language;

    @Min(value = 1, message = "Pages must be at least 1")
    @Max(value = 50000 , message = "Pages must not exceed 50000")
    private Integer pages;

    @Size(max = 2000 , message = "Description must not exceed 2000 characters")
    private String description;

    @Min(value = 0, message = "Total Copies Cannot Be Negative")
    @NotNull(message = "Total Copies is Mandatory")
    private Integer totalCopies;

    @Min(value = 0, message = "Available Copies Cannot Be Negative")
    @NotNull(message = "Available Copies is Mandatory")
    private Integer availableCopies;

    @DecimalMin(value = "0.0", inclusive = true, message = "Price Cannot be Negative")
    @Digits(integer = 8, fraction = 2, message = "Price must have atmost 8 digits and 2 decimal places")
    private BigDecimal price;

    @Size(max = 500, message = "Image URL must not exceed 500 characters")
    private String coverImageUrl;

    private Boolean active;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
    




}
