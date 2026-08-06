package com.surya.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.surya.domain.BookLoanStatus;
import com.surya.mapper.BookReviewMapper;
import com.surya.modal.Book;
import com.surya.modal.BookLoan;
import com.surya.modal.BookReview;
import com.surya.modal.User;
import com.surya.payload.dto.BookReviewDTO;
import com.surya.payload.request.CreateReviewRequest;
import com.surya.payload.request.UpdateReviewRequest;
import com.surya.payload.response.PageResponse;
import com.surya.repository.BookLoanRepository;
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
    private final BookLoanRepository bookLoanRepository;

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
    public BookReviewDTO updateReview(Long reviewId, UpdateReviewRequest request) throws Exception {

        User user = userService.getCurrentUser();

        BookReview bookReview = bookReviewRepository.findById(reviewId)
                .orElseThrow(
                        () -> new Exception("Review Not Found"));

        if (!bookReview.getUser().getId().equals(user.getId())) {
            throw new Exception("You Have Not Reviewed This Book");
        }

        bookReview.setReviewText(request.getReviewText());
        bookReview.setTitle(request.getTitle());
        bookReview.setRating(request.getRating());

        BookReview savedBookReview = bookReviewRepository.save(bookReview);

        return bookReviewMapper.toDTO(savedBookReview);
    }

    @Override
    public void deleteReview(Long reviewId) throws Exception {
        User currentUser = userService.getCurrentUser();

        BookReview bookReview = bookReviewRepository.findById(currentUser.getId())
                .orElseThrow(
                        () -> new Exception("Review Not Found With This ID : " + reviewId));

        bookReviewRepository.delete(bookReview);
    }

    @Override
    public PageResponse<BookReviewDTO> getReviewsByBook(Long id, int page, int size) throws Exception {

        Book book = bookRepository.findById(id).orElseThrow(
                () -> new Exception("Book Not Found With ID"));

        Pageable pageable = PageRequest.of(page, size, Sort.by("createdAt").descending());

        Page<BookReview> reviewPage = bookReviewRepository.findByBook(book, pageable);

        return convertToPageResponse(reviewPage);
    }

    // Helper Method
    private PageResponse<BookReviewDTO> convertToPageResponse(Page<BookReview> reviewPage) {

        List<BookReviewDTO> reviewDTOs = reviewPage.getContent()
                .stream()
                .map(bookReviewMapper::toDTO)
                .collect(Collectors.toList());

        return new PageResponse<>(
                reviewDTOs,
                reviewPage.getNumber(),
                reviewPage.getSize(),
                reviewPage.getTotalElements(),
                reviewPage.getTotalPages(),
                reviewPage.isLast(),
                reviewPage.isFirst(),
                reviewPage.isEmpty());
    }

    // Helper Method
    private boolean hasUserReadBook(Long userId, Long bookId) {

        List<BookLoan> bookLoans = bookLoanRepository.findByBookId(bookId);

        return bookLoans.stream()
                .anyMatch(loan -> loan.getUser().getId().equals(userId) &&
                        loan.getStatus() == BookLoanStatus.RETURNED);
    }

}
