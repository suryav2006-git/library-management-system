package com.surya.mapper;

import org.springframework.stereotype.Component;

import com.surya.modal.Wishlist;
import com.surya.payload.dto.WishlistDTO;

@Component
public class WishlistMapper {

    private final BookMapper bookMapper;

    private WishlistMapper(BookMapper bookMapper) {
        this.bookMapper = bookMapper;
    }

    public WishlistDTO toDTO(Wishlist wishlist) {
        if (wishlist == null) {
            return null;
        }

        WishlistDTO dto = new WishlistDTO();

        dto.setId(wishlist.getId());

        if (wishlist.getUser() != null) {
            dto.setUserId(wishlist.getUser().getId());
            dto.setUserFullName(wishlist.getUser().getFullName());
        }

        if (wishlist.getBook() != null) {
            dto.setBook(bookMapper.toDTO(wishlist.getBook()));
        }

        dto.setAddedAt(wishlist.getAddedAt());
        dto.setNotes(wishlist.getNotes());

        return dto;
    }

}
