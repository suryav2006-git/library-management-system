package com.surya.services.impl;

import org.springframework.stereotype.Service;

import com.surya.domain.BookLoanStatus;
import com.surya.modal.Book;
import com.surya.modal.User;
import com.surya.payload.dto.ReservationDTO;
import com.surya.payload.request.ReservationRequest;
import com.surya.payload.request.ReservationSearchRequest;
import com.surya.payload.response.PageResponse;
import com.surya.repository.BookLoanRepository;
import com.surya.repository.BookRepository;
import com.surya.services.ReservationService;
import com.surya.services.UserService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ReservationServiceImpl implements ReservationService {

    private final BookLoanRepository bookLoanRepository;
    private final UserService userService;
    private final BookRepository bookRepository;

    @Override
    public ReservationDTO createReservation(ReservationRequest reservationRequest) {
        return null;
    }

    @Override
    public ReservationDTO createReservationForUser(
            ReservationRequest reservationRequest, Long userId) throws Exception {

        boolean alreadyHasLoan = bookLoanRepository.existsByUserIdAndBookIdAndStatus(
                userId, reservationRequest.getBookId(), BookLoanStatus.CHECKED_OUT);

        if (alreadyHasLoan) {
            throw new Exception("You Already Have Loan On this Book");
        }

        User user = userService.getCurrentUser();

        Book book = bookRepository.findById(reservationRequest.getBookId())
                .orElseThrow(
                        () -> new Exception("Book Not Found"));

        if (reservationRequest.hasActiveReservation(userId, book.getId())) {
            throw new Exception("You Already Have A Reservation on This Book");
        }

        return null;
    }

    @Override
    public ReservationDTO cancelReservation(Long reservationId) {
        return null;
    }

    @Override
    public ReservationDTO fulfillReservation(Long reservationId) {
        return null;
    }

    @Override
    public PageResponse<ReservationDTO> getMyReservations(ReservationSearchRequest searchRequest) {
        return null;
    }

    @Override
    public PageResponse<ReservationDTO> searchReservations(ReservationSearchRequest searchRequest) {
        return null;
    }

}
