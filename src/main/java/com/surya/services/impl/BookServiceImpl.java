package com.surya.services.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.surya.payload.dto.BookDTO;
import com.surya.payload.request.BookSearchRequest;
import com.surya.payload.response.PageResponse;
import com.surya.services.BookService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    @Override
    public BookDTO createBook(BookDTO bookDTO) {
        return null;
    }

    @Override
    public List<BookDTO> createBooksBulk() {
        
        return List.of();
    }

    @Override
    public BookDTO getBookById(Long bookId) {
        
        return null;
    }

    @Override
    public BookDTO getBookByIsbn(String isbn) {
        
        return null;
    }

    @Override
    public BookDTO updateBook(Long bookId, BookDTO bookDTO) {
        
        return null;
    }

    @Override
    public void deleteBook(Long bookId) {
        
        
    }

    @Override
    public void hardDeleteBook(Long bookId) {
        
    }

    @Override
    public PageResponse<BookDTO> searchBooksWithFilters(BookSearchRequest searchRequest) {
        
        return null;
    }

    @Override
    public long getTotalActiveBooks() {
        
        return 0;
    }

    @Override
    public long getTotalAvailableBooks() {
        
        return 0;
    }






}
