package com.surya.mapper;

import java.util.stream.Collectors;

import com.surya.modal.Genre;
import com.surya.payload.dto.GenreDTO;

public class GenreMapper {

    public static GenreDTO toDTO(Genre savedGenre) {
        if(savedGenre == null ) {
            return null;
        }
        
        GenreDTO dto = GenreDTO.builder()
            .id(savedGenre.getId())
            .code(savedGenre.getCode())
            .name(savedGenre.getName())
            .description(savedGenre.getDescription())
            .displayOrder(savedGenre.getDisplayOrder())
            .active(savedGenre.getActive())
            .createdAt(savedGenre.getCreatedAt())
            .updatedAt(savedGenre.getUpdatedAt())
            .build();

        if(savedGenre.getParentGenere()!=null) {
            dto.setParentGenreId(savedGenre.getParentGenere().getId());
            dto.setParentGenreName(savedGenre.getParentGenere().getName());
        }

        if(savedGenre.getSubGenres() != null && !savedGenre.getSubGenres().isEmpty()) {
            dto.setSubGenre(savedGenre.getSubGenres().stream()
                .filter(subGenre-> subGenre.getActive())
                .map(subGenre -> toDTO(subGenre)).collect(Collectors.toList()));
        }

        

        // dto.setBookCount((long) (savedGenre.get)  );
        return dto;
    }
}
