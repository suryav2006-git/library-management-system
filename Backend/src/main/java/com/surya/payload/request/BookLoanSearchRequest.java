package com.surya.payload.request;

import java.time.LocalDate;

import com.surya.domain.BookLoanStatus;

public class BookLoanSearchRequest {

    private Long userId;

    private Long bookId;

    private BookLoanStatus status;

    private Boolean overdueOnly;

    private Boolean unpaidFinesOnly;

    private LocalDate startDate;

    private LocalDate endDate;

    private Integer page = 0;

    private Integer size = 20;

    private String sortBy = "createdAt";

    private String sortDirection = "DESC";

}
