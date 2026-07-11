package com.surya.payload.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

import com.surya.domain.BookLoanStatus;
import com.surya.domain.BookLoanType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookLoanDTO {

    private Long Id;

    private Long userId;

    private String userName;

    private String userEmail;

    private Long bookId;

    private String bookTitle;

    private String bookIsbn;

    private String bookAuthor;

    private String bookCoverImage;

    private BookLoanType type;

    private BookLoanStatus status;

    private LocalDate checkoutDate;

    private LocalDate dueDate;

    private Long remainingDays;

    private LocalDate returnDate;

    private Integer renewalCount;

    private Integer maxRenewals;

    private BigDecimal fineAmount;

    private Boolean finePaid;

    private String notes;

    private Boolean isOverDue;

    private Integer overdueDays;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

}
