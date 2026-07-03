package com.surya.payload.dto;

import java.time.LocalDateTime;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SubscriptionPlanDTO {

    private Long id;

    @NotBlank(message = "Plan Code is Mandatory")
    private String planCode;

    @NotBlank(message = "Plane Name is Mandatory")
    private String name;

    private String description;

    @NotNull(message = "Duration is Mandatory")
    @Positive(message = "Duration Must be Positive")
    private Integer durationDays;

    @NotNull(message = "Price is Mandatory")
    @Positive(message = "Price Must be Positive")
    private Long price;

    private String currency;

    @NotNull(message = "Max Books Allowed is Mandatory")
    @Positive(message = "Max Books Must be Positive")
    private Integer maxBooksAllowed;

    @NotNull(message = "Max Days Per Book is Mandatory")
    @Positive(message = "Max Days Must Be Positive")
    private Integer maxDaysPerBook;

    private Integer displayOrder;

    private Boolean isActive;

    private Boolean isFeatured;

    private String badgeText;

    private String adminNotes;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private String createdBy;

    private String updatedBy;

}
