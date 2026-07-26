package com.surya.services.impl;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
    public BookLoanDTO renewCheckout(RenewalRequest renewalRequest) throws Exception {

        BookLoan bookLoan = bookLoanRepository.findById(renewalRequest.getBookLoanId())
                .orElseThrow(
                        () -> new Exception("Bookloan Not Found"));

        if (!bookLoan.canRenew()) {
            throw new BookException("Book Cannot Be Renewed");
        }

        bookLoan.setDueDate(bookLoan.getDueDate()
                .plusDays(renewalRequest.getExtensionDays()));

        bookLoan.setRenewalCount(bookLoan.getRenewalCount() + 1);

        bookLoan.setNotes("Book Renamed By User");

        BookLoan savedBookLoan = bookLoanRepository.save(bookLoan);

        return bookLoanMapper.toDTO(savedBookLoan);
    }

    @Override
    public PageResponse<BookLoanDTO> getMyBookLoans(BookLoanStatus status, int page, int size) throws Exception {

        User currentUser = userService.getCurrentUser();
        Page<BookLoan> bookLoanPage;

        if (status != null) {
            Pageable pageable = PageRequest.of(page, size, Sort.by("dueDate").ascending());
            bookLoanPage = bookLoanRepository.findByStatusAndUser(status, currentUser, pageable);
        } else {
            Pageable pageable = PageRequest.of(page, size, Sort.by("createdAt").descending());
            bookLoanPage = bookLoanRepository.findByUserId(currentUser.getId(), pageable);
        }

        return convertToPageResponse(bookLoanPage);
    }

    @Override
    public PageResponse<BookLoanDTO> getBookLoans(BookLoanSearchRequest searchRequest) throws Exception {

        Pageable pageable = createPageable(
                searchRequest.getPage(),
                searchRequest.getSize(),
                searchRequest.getSortBy(),
                searchRequest.getSortDirection());

        Page<BookLoan> bookLoanPage;

        if (Boolean.TRUE.equals(searchRequest.getOverdueOnly())) {
            bookLoanPage = bookLoanRepository.findOverdueBookLoans(LocalDate.now(), pageable);
        }

        else if (searchRequest.getUserId() != null) {
            bookLoanPage = bookLoanRepository.findByUserId(searchRequest.getUserId(), pageable);
        }

        else if (searchRequest.getBookId() != null) {
            bookLoanPage = bookLoanRepository.findByBookId(searchRequest.getBookId(), pageable);
        } else if (searchRequest.getStatus() != null) {
            bookLoanPage = bookLoanRepository.findByStatus(searchRequest.getStatus(), pageable);
        } else if (searchRequest.getStartDate() != null && searchRequest.getEndDate() != null) {
            bookLoanPage = bookLoanRepository.findBookLoansByDateRange(
                    searchRequest.getStartDate(),
                    searchRequest.getEndDate(),
                    pageable);
        } else {
            bookLoanPage = bookLoanRepository.findAll(pageable);
        }

        return convertToPageResponse(bookLoanPage);
    }

    @Override
    public int updateOverdueBookLoan() {

        Pageable pageable = PageRequest.of(0, 1000);
        Page<BookLoan> overduePage = bookLoanRepository.findOverdueBookLoans(LocalDate.now(), pageable);

        int updateCount = 0;

        for (BookLoan bookLoan : overduePage.getContent()) {
            if (bookLoan.getStatus() == BookLoanStatus.CHECKED_OUT) {
                bookLoan.setStatus(BookLoanStatus.OVERDUE);
                bookLoan.setIsOverdue(true);

                int overdueDays = calculateOverDueDate(bookLoan.getDueDate(), LocalDate.now());

                // todo Calculate Fine

                bookLoanRepository.save(bookLoan);
                updateCount++;
            }
        }
        return updateCount;
    }

    private Pageable createPageable(int page, int size, String sortBy, String sortDirection) {
        size = Math.min(size, 100);
        size = Math.max(size, 1);

        Sort sort = sortDirection.equalsIgnoreCase("ASC")
                ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();

        return PageRequest.of(page, size, sort);
    }

    private PageResponse<BookLoanDTO> convertToPageResponse(Page<BookLoan> bookLoanPage) {
        List<BookLoanDTO> bookLoanDTOs = bookLoanPage.getContent()
                .stream()
                .map(bookLoanMapper::toDTO)
                .collect(Collectors.toList());

        return new PageResponse<>(
                bookLoanDTOs,
                bookLoanPage.getNumber(),
                bookLoanPage.getSize(),
                bookLoanPage.getTotalElements(),
                bookLoanPage.getTotalPages(),
                bookLoanPage.isLast(),
                bookLoanPage.isFirst(),
                bookLoanPage.isEmpty());

    }

    public int calculateOverDueDate(LocalDate dueDate, LocalDate today) {
        if (today.isBefore(dueDate) || today.isEqual(dueDate)) {
            return 0;
        }

        return (int) ChronoUnit.DAYS.between(dueDate, today);
    }

}
