package com.surya.services;

import com.surya.payload.dto.WishlistDTO;
import com.surya.payload.response.PageResponse;

public interface WishlistService {

    WishlistDTO addToWishlist(Long bookId, String notes) throws Exception;

    void removeFromWishlist(Long bookId) throws Exception;

    PageResponse<WishlistDTO> getMyWishList(int page, int size) throws Exception;

}
