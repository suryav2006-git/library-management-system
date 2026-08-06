package com.surya.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.surya.payload.dto.BookReviewDTO;
import com.surya.payload.request.CreateReviewRequest;
import com.surya.payload.request.UpdateReviewRequest;
import com.surya.payload.response.ApiResponse;
import com.surya.payload.response.PageResponse;
import com.surya.services.BookReviewService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor

@RequestMapping("/api/reviews")
public class BookReviewController {

    private final BookReviewService bookReviewService;

    @PostMapping
    public ResponseEntity<?> createReview(
            @Valid @RequestBody CreateReviewRequest request) throws Exception {
        BookReviewDTO reviewDTO = bookReviewService.createReview(request);
        return ResponseEntity.ok(reviewDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateReview(
            @PathVariable Long id,
            @Valid @RequestBody UpdateReviewRequest request) throws Exception {
        BookReviewDTO reviewDTO = bookReviewService.updateReview(id, request);
        return ResponseEntity.ok(reviewDTO);
    }

    @DeleteMapping("/{reviewId}")
    public ResponseEntity<?> deleteReview(
            @PathVariable Long reviewId) throws Exception {
        bookReviewService.deleteReview(reviewId);
        return ResponseEntity.ok(
                new ApiResponse("Review Deleted Successfully", true));
    }

    @GetMapping("/book/{bookId}")
    public ResponseEntity<PageResponse<BookReviewDTO>> getReviewsByBook(
            @PathVariable Long bookId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) throws Exception {
        PageResponse<BookReviewDTO> reviews = bookReviewService.getReviewsByBook(bookId, page, size);
        return ResponseEntity.ok(reviews);
    }

}
