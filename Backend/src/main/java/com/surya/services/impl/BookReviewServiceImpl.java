package com.surya.services.impl;

import org.springframework.stereotype.Service;

import com.surya.mapper.BookReviewMapper;
import com.surya.modal.Book;
import com.surya.modal.BookReview;
import com.surya.modal.User;
import com.surya.payload.dto.BookReviewDTO;
import com.surya.payload.request.CreateReviewRequest;
import com.surya.payload.request.UpdateReviewRequest;
import com.surya.payload.response.PageResponse;
import com.surya.repository.BookRepository;
import com.surya.repository.BookReviewRepository;
import com.surya.services.BookReviewService;
import com.surya.services.UserService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BookReviewServiceImpl implements BookReviewService {

    private final BookReviewRepository bookReviewRepository;
    private final UserService userService;
    private final BookRepository bookRepository;
    private final BookReviewMapper bookReviewMapper;

    @Override
    public BookReviewDTO createReview(CreateReviewRequest request) throws Exception {

        User user = userService.getCurrentUser();

        Book book = bookRepository.findById(request.getBookId())
                .orElseThrow(
                        () -> new Exception("Book Not Found"));

        if (bookReviewRepository.existsByUserIdAndBookId(user.getId(), book.getId())) {
            throw new Exception("You Have Already Reviewed This Book");
        }

        boolean hasReadBook = hasUserReadBook(user.getId(), book.getId());

        if (!hasReadBook) {
            throw new Exception("You Have Not Read this Book");
        }

        BookReview bookReview = new BookReview();

        bookReview.setUser(user);
        bookReview.setBook(book);
        bookReview.setRating(request.getRating());
        bookReview.setReviewText(request.getReviewText());
        bookReview.setTitle(request.getTitle());

        BookReview savedBookReview = bookReviewRepository.save(bookReview);

        return bookReviewMapper.toDTO(savedBookReview);
    }

    @Override
    public BookReviewDTO updateReview(Long reviewId, UpdateReviewRequest request) {
        return null;
    }

    @Override
    public void deleteReview(Long reviewId) {

    }

    @Override
    public PageResponse<BookReviewDTO> getReviewsByBook(Long id, int page, int size) {
        return null;
    }

    // Helper Method
    private boolean hasUserReadBook(Long id, Long id1) {
        return false;
    }

}
