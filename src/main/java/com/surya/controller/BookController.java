package com.surya.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.surya.exception.BookException;
import com.surya.payload.dto.BookDTO;
import com.surya.services.BookService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/books")
public class BookController {

    private final BookService bookService;

    @PostMapping
    public ResponseEntity<BookDTO> createBook(
        @Valid @RequestBody BookDTO bookDTO
    ) throws BookException {

        BookDTO createdBook = bookService.createBook(bookDTO);
        return ResponseEntity.ok(createdBook);

    }


    @PostMapping("/bulk")
    public ResponseEntity<?> createBooksBulk(
        @Valid @RequestBody List<BookDTO> bookDTOs
    ) throws BookException {

        List<BookDTO> createdBooks = bookService.createBooksBulk(bookDTOs);
        return ResponseEntity.ok(createdBooks);

    }




}
