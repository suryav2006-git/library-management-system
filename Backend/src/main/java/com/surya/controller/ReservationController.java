package com.surya.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.surya.domain.ReservationStatus;
import com.surya.exception.BookException;
import com.surya.payload.dto.ReservationDTO;
import com.surya.payload.request.ReservationRequest;
import com.surya.payload.request.ReservationSearchRequest;
import com.surya.payload.response.PageResponse;
import com.surya.services.ReservationService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/reservations")
public class ReservationController {

    private final ReservationService reservationService;

    @PostMapping()
    public ResponseEntity<?> createReservation(
            @Valid @RequestBody ReservationRequest reservationRequest) throws Exception {

        ReservationDTO reservationDTO = reservationService.createReservation(reservationRequest);

        return ResponseEntity.ok(reservationDTO);
    }

    @PostMapping("/user/{userId}")
    public ResponseEntity<?> createReservationForUser(
            @PathVariable Long userId,
            @Valid @RequestBody ReservationRequest reservationRequest) throws Exception {
        ReservationDTO reservation = reservationService.createReservationForUser(reservationRequest, userId);
        return new ResponseEntity<>(reservation, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> cancelReservation(
            @PathVariable Long id) throws Exception {
        ReservationDTO reservation = reservationService.cancelReservation(id);
        return ResponseEntity.ok(reservation);
    }

    @PostMapping("/{id}/fulfill")
    public ResponseEntity<?> fulfillReservation(
            @PathVariable Long id) throws BookException, Exception {
        ReservationDTO reservationDTO = reservationService.fulfillReservation(id);
        return ResponseEntity.ok(reservationDTO);
    }

    @GetMapping("/my")
    public ResponseEntity<PageResponse<ReservationDTO>> getMyResponse(
            @RequestParam(required = false) ReservationStatus status,
            @RequestParam(required = false) Boolean activeOnly,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size,
            @RequestParam(defaultValue = "reservedAt") String sortBy,
            @RequestParam(defaultValue = "DESC") String sortDirection) throws Exception {
        ReservationSearchRequest searchRequest = new ReservationSearchRequest();

        searchRequest.setStatus(status);
        searchRequest.setActiveOnly(activeOnly);
        searchRequest.setPage(page);
        searchRequest.setSize(size);
        searchRequest.setSortBy(sortBy);
        searchRequest.setSortDirection(sortDirection);

        PageResponse<ReservationDTO> reservations = reservationService.getMyReservations(searchRequest);
        return ResponseEntity.ok(reservations);
    }

    @GetMapping
    public ResponseEntity<PageResponse<ReservationDTO>> searchReservations(
            @RequestParam(required = false) Long userId,
            @RequestParam(required = false) Long bookId,
            @RequestParam(required = false) ReservationStatus status,
            @RequestParam(required = false) Boolean activeOnly,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size,
            @RequestParam(defaultValue = "reservedAt") String sortBy,
            @RequestParam(defaultValue = "DESC") String sortDirection) {
        ReservationSearchRequest searchRequest = new ReservationSearchRequest();

        searchRequest.setUserId(userId);
        searchRequest.setBookId(bookId);
        searchRequest.setStatus(status);
        searchRequest.setActiveOnly(activeOnly);
        searchRequest.setPage(page);
        searchRequest.setSize(size);
        searchRequest.setSortBy(sortBy);
        searchRequest.setSortDirection(sortDirection);

        PageResponse<ReservationDTO> reservations = reservationService.searchReservations(searchRequest);
        return ResponseEntity.ok(reservations);
    }

}
