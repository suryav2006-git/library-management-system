package com.surya.mapper;

import org.springframework.stereotype.Component;

import com.surya.modal.Book;
import com.surya.payload.dto.BookDTO;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor


public class BookMapper {

    public BookDTO toDTO(Book book) {
        if(book == null) {
            return null;
        }

        return BookDTO.builder()
            .id(book.getId())
            .title(book.getTitle())
            .author(book.getAuthor())
            .isbn(book.getIsbn())
            .genreId(book.getGenre().getId())
            .genreName(book.getGenre().getName())
            .genreCode(book.getGenre().getCode())
            .publisher(book.getPublisher())
            .publishedDate(book.getPublishedDate())
            .language(book.getLanguage())
            .pages(book.getPages())
            .description(book.getDescription())
            .totalCopies(book.getTotalCopies())
            .availableCopies(book.getAvailableCopies())
            .price(book.getPrice())
            .coverImageUrl(book.getCoverImageUrl())
            .active(book.getActive())
            .createdAt(book.getCreatedAt())
            .updatedAt(book.getUpdatedAt())
            .build();

        
    }




}
