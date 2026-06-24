package com.surya.services;

import java.util.List;

import com.surya.exception.BookException;
import com.surya.payload.dto.BookDTO;
import com.surya.payload.request.BookSearchRequest;
import com.surya.payload.response.PageResponse;

public interface BookService {

    BookDTO createBook(BookDTO bookDTO) throws BookException;

    List<BookDTO> createBooksBulk(List<BookDTO> bookDTOList) throws BookException;

    BookDTO getBookById(Long bookId) throws BookException;

    BookDTO getBookByIsbn(String isbn) throws BookException;

    BookDTO updateBook (Long bookId, BookDTO bookDTO) throws BookException;

    void deleteBook (Long bookId) throws BookException;

    void hardDeleteBook (Long bookId) throws BookException;

    PageResponse<BookDTO> searchBooksWithFilters(
        BookSearchRequest searchRequest
    );

    long getTotalActiveBooks();

    long getTotalAvailableBooks();



    
} 
