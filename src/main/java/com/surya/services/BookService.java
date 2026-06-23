package com.surya.services;

import java.util.List;

import com.surya.payload.dto.BookDTO;
import com.surya.payload.request.BookSearchRequest;
import com.surya.payload.response.PageResponse;

public interface BookService {

    BookDTO createBook(BookDTO bookDTO);

    List<BookDTO> createBooksBulk();

    BookDTO getBookById(Long bookId);

    BookDTO getBookByIsbn(String isbn);

    BookDTO updateBook (Long bookId, BookDTO bookDTO);

    void deleteBook (Long bookId);

    void hardDeleteBook (Long bookId);

    PageResponse<BookDTO> searchBooksWithFilters(
        BookSearchRequest searchRequest
    );

    long getTotalActiveBooks();

    long getTotalAvailableBooks();



    
} 
