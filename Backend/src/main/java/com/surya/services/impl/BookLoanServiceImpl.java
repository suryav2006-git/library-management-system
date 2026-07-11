package com.surya.services.impl;

import org.springframework.stereotype.Service;

import com.surya.domain.BookLoanStatus;
import com.surya.payload.dto.BookLoanDTO;
import com.surya.payload.request.BookLoanSearchRequest;
import com.surya.payload.request.CheckinRequest;
import com.surya.payload.request.CheckoutRequest;
import com.surya.payload.request.RenewalRequest;
import com.surya.payload.response.PageResponse;
import com.surya.repository.BookLoanRepository;
import com.surya.services.BookLoanService;
import com.surya.services.UserService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BookLoanServiceImpl implements BookLoanService {

    private final BookLoanRepository bookLoanRepository;
    private final UserService userService;

    @Override
    public BookLoanDTO checkoutBook(CheckoutRequest checkoutRequest) {

        return null;
    }

    @Override
    public BookLoanDTO checkoutBookForUser(Long userId, CheckoutRequest checkoutRequest) {

        return null;
    }

    @Override
    public BookLoanDTO checkinBook(CheckinRequest checkinRequest, String bookId) {
        return null;
    }

    @Override
    public BookLoanDTO renewCheckout(RenewalRequest renewalRequest) {
        return null;
    }

    @Override
    public PageResponse<BookLoanDTO> getMyBookLoans(BookLoanStatus status, int page, int size) {
        return null;
    }

    @Override
    public PageResponse<BookLoanDTO> getBookLoans(BookLoanSearchRequest request) {
        return null;
    }

    @Override
    public int updateOverdueBookLoan() {
        return 0;
    }

}
