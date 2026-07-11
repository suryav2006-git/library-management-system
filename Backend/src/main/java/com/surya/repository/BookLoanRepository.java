package com.surya.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.surya.domain.BookLoanStatus;
import com.surya.modal.BookLoan;

public interface BookLoanRepository extends JpaRepository<BookLoan, Long> {

    Page<BookLoan> findByStatus(BookLoanStatus status, Pageable pageable);

    Page<BookLoan> findByUserId(Long userId, Pageable pageable);

    Page<BookLoan> findByUserIdAndStatus(Long userId, BookLoanStatus status, Pageable pageable);

}
