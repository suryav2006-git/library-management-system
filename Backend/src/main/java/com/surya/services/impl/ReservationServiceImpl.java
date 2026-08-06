package com.surya.services.impl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.surya.domain.BookLoanStatus;
import com.surya.domain.ReservationStatus;
import com.surya.domain.UserRole;
import com.surya.mapper.ReservationMapper;
import com.surya.modal.Book;
import com.surya.modal.Reservation;
import com.surya.modal.User;
import com.surya.payload.dto.ReservationDTO;
import com.surya.payload.request.CheckoutRequest;
import com.surya.payload.request.ReservationRequest;
import com.surya.payload.request.ReservationSearchRequest;
import com.surya.payload.response.PageResponse;
import com.surya.repository.BookLoanRepository;
import com.surya.repository.BookRepository;
import com.surya.repository.ReservationRepository;
import com.surya.services.BookLoanService;
import com.surya.services.ReservationService;
import com.surya.services.UserService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ReservationServiceImpl implements ReservationService {

    private final BookLoanRepository bookLoanRepository;
    private final UserService userService;
    private final BookRepository bookRepository;
    private final ReservationRepository reservationRepository;
    private final ReservationMapper reservationMapper;
    private final BookLoanService bookLoanService;

    int MAX_RESERVATION = 5;

    @Override
    public ReservationDTO createReservation(ReservationRequest reservationRequest) throws Exception {
        User user = userService.getCurrentUser();
        return createReservationForUser(reservationRequest, user.getId());
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

        if (book.getAvailableCopies() > 0) {
            throw new Exception("Book Is Already Available");
        }

        long activeReservation = reservationRepository.countActiveReservationsByUser(userId);

        if (activeReservation >= MAX_RESERVATION) {
            throw new Exception(
                    "You Have Reserved " + MAX_RESERVATION + " Times");
        }

        Reservation reservation = new Reservation();
        reservation.setUser(user);
        reservation.setBook(book);
        reservation.setStatus(ReservationStatus.PENDING);
        reservation.setReservedAt(LocalDateTime.now());
        reservation.setNotificationSent(false);
        reservation.setNotes(reservationRequest.getNotes());

        long pendingCount = reservationRepository.countPendingReservationsByBook(userId);

        reservation.setQueuePosition((int) pendingCount + 1);

        Reservation savedReservation = reservationRepository.save(reservation);

        return reservationMapper.toDTO(savedReservation);
    }

    @Override
    public ReservationDTO cancelReservation(Long reservationId) throws Exception {

        Reservation reservation = reservationRepository.findById(reservationId)
                .orElseThrow(
                        () -> new Exception(
                                "Reservation Not Found With Id : " + reservationId));

        User currentUser = userService.getCurrentUser();

        if (!reservation.getUser().getId().equals(currentUser.getId())
                && currentUser.getRole() != UserRole.ROLE_ADMIN) {
            throw new Exception("You Can Only Cancel Your Own Reservation");
        }

        if (!reservation.canBeCancelled()) {
            throw new Exception("Reservation Cannot Be Cancelled (Current Status : " + reservation);
        }

        reservation.setStatus(ReservationStatus.CANCELLED);
        reservation.setCancelledAt(LocalDateTime.now());

        Reservation savedReservation = reservationRepository.save(reservation);

        return reservationMapper.toDTO(savedReservation);
    }

    @Override
    public ReservationDTO fulfillReservation(Long reservationId) throws Exception {

        Reservation reservation = reservationRepository.findById(reservationId)
                .orElseThrow(
                        () -> new Exception(
                                "Reservation Not Found With ID : " + reservationId));

        if (reservation.getBook().getAvailableCopies() <= 0) {
            throw new Exception(
                    "Reservation Is Not Available For Pickup ");
        }

        reservation.setStatus(ReservationStatus.FULFILLED);
        reservation.setFulfilledAt(LocalDateTime.now());

        Reservation savedReservation = reservationRepository.save(reservation);

        CheckoutRequest request = new CheckoutRequest();
        request.setBookId(reservation.getBook().getId());
        request.setNotes("Assigned Book By Admin");

        bookLoanService.checkoutBookForUser(reservation.getUser().getId(), request);

        return reservationMapper.toDTO(savedReservation);
    }

    @Override
    public PageResponse<ReservationDTO> getMyReservations(ReservationSearchRequest searchRequest) throws Exception {

        User user = userService.getCurrentUser();

        searchRequest.setUserId(user.getId());

        return searchReservations(searchRequest);
    }

    @Override
    public PageResponse<ReservationDTO> searchReservations(ReservationSearchRequest searchRequest) {

        Pageable pageable = createPageable(searchRequest);

        Page<Reservation> reservationPage = reservationRepository.searchReservationsWithFilters(
                searchRequest.getUserId(),
                searchRequest.getBookId(),
                searchRequest.getStatus(),
                searchRequest.getActiveOnly() != null ? searchRequest.getActiveOnly() : false,
                pageable);

        return buildPageResponse(reservationPage);
    }

    private PageResponse<ReservationDTO> buildPageResponse(Page<Reservation> reservationPage) {

        List<ReservationDTO> dtos = reservationPage.getContent().stream()
                .map(reservationMapper::toDTO)
                .toList();

        PageResponse<ReservationDTO> response = new PageResponse<>();

        response.setContent(dtos);
        response.setPageNumber(reservationPage.getNumber());
        response.setPageSize(reservationPage.getSize());
        response.setTotalElements(reservationPage.getTotalElements());
        response.setTotalPages(reservationPage.getTotalPages());
        response.setLast(reservationPage.isLast());

        return response;
    }

    private Pageable createPageable(ReservationSearchRequest searchRequest) {
        Sort sort = "ASC".equalsIgnoreCase(searchRequest.getSortDirection())
                ? Sort.by(searchRequest.getSortBy()).ascending()
                : Sort.by(searchRequest.getSortBy()).descending();

        return PageRequest.of(searchRequest.getPage(), searchRequest.getSize(), sort);
    }

}
