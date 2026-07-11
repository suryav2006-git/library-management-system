package com.surya.services;

import com.surya.domain.BookLoanStatus;
import com.surya.payload.dto.BookLoanDTO;
import com.surya.payload.request.BookLoanSearchRequest;
import com.surya.payload.request.CheckinRequest;
import com.surya.payload.request.CheckoutRequest;
import com.surya.payload.request.RenewalRequest;
import com.surya.payload.response.PageResponse;

public interface BookLoanService {

    BookLoanDTO checkoutBook(CheckoutRequest checkoutRequest);

    BookLoanDTO checkoutBookForUser(Long userId, CheckoutRequest checkoutRequest);

    BookLoanDTO checkinBook(CheckinRequest checkinRequest, String bookId);

    BookLoanDTO renewCheckout(RenewalRequest renewalRequest);

    PageResponse<BookLoanDTO> getMyBookLoans(
            BookLoanStatus status, int page, int size);

    PageResponse<BookLoanDTO> getBookLoans(
            BookLoanSearchRequest request);

    int updateOverdueBookLoan();

}
