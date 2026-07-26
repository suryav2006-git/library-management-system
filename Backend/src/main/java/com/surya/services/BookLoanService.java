package com.surya.services;

import com.surya.domain.BookLoanStatus;
import com.surya.payload.dto.BookLoanDTO;
import com.surya.payload.request.BookLoanSearchRequest;
import com.surya.payload.request.CheckinRequest;
import com.surya.payload.request.CheckoutRequest;
import com.surya.payload.request.RenewalRequest;
import com.surya.payload.response.PageResponse;

public interface BookLoanService {

        BookLoanDTO checkoutBook(CheckoutRequest checkoutRequest) throws Exception;

        BookLoanDTO checkoutBookForUser(Long userId, CheckoutRequest checkoutRequest) throws Exception;

        BookLoanDTO checkinBook(CheckinRequest checkinRequest) throws Exception;

        BookLoanDTO renewCheckout(RenewalRequest renewalRequest) throws Exception;

        PageResponse<BookLoanDTO> getMyBookLoans(
                        BookLoanStatus status, int page, int size) throws Exception;

        PageResponse<BookLoanDTO> getBookLoans(
                        BookLoanSearchRequest request) throws Exception;

        int updateOverdueBookLoan();

}
