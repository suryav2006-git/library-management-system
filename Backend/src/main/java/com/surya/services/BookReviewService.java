package com.surya.services;

import com.surya.payload.dto.BookReviewDTO;
import com.surya.payload.request.CreateReviewRequest;
import com.surya.payload.request.UpdateReviewRequest;
import com.surya.payload.response.PageResponse;

public interface BookReviewService {

    BookReviewDTO createReview(CreateReviewRequest request) throws Exception;

    BookReviewDTO updateReview(Long reviewId, UpdateReviewRequest request) throws Exception;

    void deleteReview(Long reviewId);

    PageResponse<BookReviewDTO> getReviewsByBook(Long id, int page, int size);

}
