package com.surya.payload.request;

import java.util.List;

import org.springframework.data.repository.query.Param;

import com.surya.modal.Reservation;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
public class ReservationRequest {

    @NotNull(message = "Book ID is Mandatory")
    private Long bookId;

    private String notes;

    public boolean hasActiveReservation(Long userId, Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'hasActiveReservation'");
    }

    List<Reservation> findPendingReservationByBook(@Param("bookId") Long bookId);

}
