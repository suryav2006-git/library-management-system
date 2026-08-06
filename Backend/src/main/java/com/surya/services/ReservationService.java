package com.surya.services;

import com.surya.payload.dto.ReservationDTO;
import com.surya.payload.request.ReservationRequest;
import com.surya.payload.request.ReservationSearchRequest;
import com.surya.payload.response.PageResponse;

public interface ReservationService {

    ReservationDTO createReservation(ReservationRequest reservationRequest) throws Exception;

    ReservationDTO createReservationForUser(
            ReservationRequest reservationRequest, Long userId) throws Exception;

    ReservationDTO cancelReservation(Long reservationId) throws Exception;

    ReservationDTO fulfillReservation(Long reservationId) throws Exception;

    PageResponse<ReservationDTO> getMyReservations(ReservationSearchRequest searchRequest) throws Exception;

    PageResponse<ReservationDTO> searchReservations(ReservationSearchRequest searchRequest);

}