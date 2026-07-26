package com.surya.services.impl;

import java.time.LocalDate;

import org.springframework.stereotype.Service;

import com.surya.domain.BookLoanStatus;
import com.surya.domain.BookLoanType;
import com.surya.exception.BookException;
import com.surya.mapper.BookLoanMapper;
import com.surya.modal.Book;
import com.surya.modal.BookLoan;
import com.surya.modal.User;
import com.surya.payload.dto.BookLoanDTO;
import com.surya.payload.dto.SubscriptionDTO;
import com.surya.payload.request.BookLoanSearchRequest;
import com.surya.payload.request.CheckinRequest;
import com.surya.payload.request.CheckoutRequest;
import com.surya.payload.request.RenewalRequest;
import com.surya.payload.response.PageResponse;
import com.surya.repository.BookLoanRepository;
import com.surya.repository.BookRepository;
import com.surya.services.BookLoanService;
import com.surya.services.SubscriptionService;
import com.surya.services.UserService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BookLoanServiceImpl implements BookLoanService {

    private final BookLoanRepository bookLoanRepository;
    private final UserService userService;
    private final SubscriptionService subscriptionService;
    private final BookRepository bookRepository;
    private final BookLoanMapper bookLoanMapper;

    @Override
    public BookLoanDTO checkoutBook(CheckoutRequest checkoutRequest) throws Exception {

        User user = userService.getCurrentUser();

        return checkoutBookForUser(user.getId(), checkoutRequest);
    }

    @Override
    public BookLoanDTO checkoutBookForUser(Long userId, CheckoutRequest checkoutRequest) throws Exception {

        User user = userService.findById(userId);

        SubscriptionDTO subscription = subscriptionService.getUsersActiveSubscription(user.getId());

        Book book = bookRepository.findById(checkoutRequest.getBookId())
                .orElseThrow(
                        () -> new BookException("Book Not Found With Id" + checkoutRequest.getBookId()));

        if (!book.getActive()) {
            throw new BookException("Book is Not active");
        }
        if (book.getAvailableCopies() <= 0) {
            throw new BookException("Book is Not Available");
        }

        if (bookLoanRepository.hasActiveCheckout(userId, book.getId())) {
            throw new BookException("Book Already Has Active Checkout");
        }

        long activeCheckouts = bookLoanRepository.countActiveBookLoansByUser(userId);
        int maxBookAllowed = subscription.getMaxBooksAllowed();

        if (activeCheckouts >= maxBookAllowed) {
            throw new Exception("You Have Reached You Maximum Allowed Book Count");
        }

        long overdueCount = bookLoanRepository.countOverdueBookLoansByUser(userId);

        if (overdueCount > 0) {
            throw new Exception("First Return Old Overdue Book!");
        }

        BookLoan bookLoan = BookLoan.builder()
                .user(user)
                .book(book)
                .type(BookLoanType.CHECKOUT)
                .status(BookLoanStatus.CHECKED_OUT)
                .checkoutDate(LocalDate.now())
                .dueDate(LocalDate.now().plusDays(checkoutRequest.getCheckoutDays()))
                .renewalCount(0)
                .maxRenewals(2)
                .notes(checkoutRequest.getNotes())
                .isOverdue(false)
                .overdueDays(0)
                .build();

        book.setAvailableCopies(book.getAvailableCopies() - 1);
        bookRepository.save(book);

        BookLoan savedBookLoan = bookLoanRepository.save(bookLoan);

        return bookLoanMapper.toDTO(savedBookLoan);
    }

    @Override
    public BookLoanDTO checkinBook(CheckinRequest checkinRequest, String bookId) throws Exception {

        BookLoan bookLoan = bookLoanRepository.findById(checkinRequest.getBookLoanId())
                .orElseThrow(
                        () -> new Exception("Bookloan Not Found"));
        if (!bookLoan.isActive()) {
            throw new Exception("Book Loan is NOT Active");
        }

        bookLoan.setReturnDate(LocalDate.now());

        BookLoanStatus condition = checkinRequest.getCondition();

        if (condition == null) {
            condition = BookLoanStatus.RETURNED;
        }

        bookLoan.setStatus(condition);

        // todo Fine

        bookLoan.setOverdueDays(0);
        bookLoan.setIsOverdue(false);

        bookLoan.setNotes("Book Returned By User");

        if (condition != BookLoanStatus.LOST) {
            Book book = bookLoan.getBook();
            book.setAvailableCopies(book.getAvailableCopies() + 1);
            bookRepository.save(book);

            // todo process next reservation

        }

        BookLoan savedBookLoan = bookLoanRepository.save(bookLoan);

        return bookLoanMapper.toDTO(savedBookLoan);
    }

    @Override
    public BookLoanDTO renewCheckout(RenewalRequest renewalRequest) {
        return null;
    }

    @Override
    public PageResponse<BookLoanDTO> getMyBookLoans(BookLoanStatus status, int page, int size) {
        return null;
    }

    @Override
    public PageResponse<BookLoanDTO> getBookLoans(BookLoanSearchRequest request) {
        return null;
    }

    @Override
    public int updateOverdueBookLoan() {
        return 0;
    }

}
