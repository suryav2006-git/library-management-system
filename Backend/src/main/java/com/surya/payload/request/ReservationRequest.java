package com.surya.payload.request;

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
        throw new UnsupportedOperationException("Unimplemented method 'hasActiveReservation'");
    }

    // List<Reservation> findPendingReservationsByBook(@Param("bookId") Long
    // bookId);

}
