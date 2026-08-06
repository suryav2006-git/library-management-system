package com.surya.payload.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class WishlistDTO {

    private Long id;

    private Long userId;

    private String userFullName;

    private BookDTO book;

    private LocalDateTime addedAt;

    private String notes;

}
