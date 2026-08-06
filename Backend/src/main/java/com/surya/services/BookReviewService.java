package com.surya.services;

import com.surya.payload.dto.BookReviewDTO;
import com.surya.payload.request.CreateReviewRequest;

public interface BookReviewService {

    BookReviewDTO createReview(CreateReviewRequest request);

}
