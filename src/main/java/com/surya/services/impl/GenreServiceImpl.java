package com.surya.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.surya.mapper.GenreMapper;
import com.surya.modal.Genre;
import com.surya.payload.dto.GenreDTO;
import com.surya.repository.GenreRepository;
import com.surya.services.GenreService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class GenreServiceImpl implements GenreService{

    
    private final GenreRepository genreRepository;

    @Override
    public GenreDTO createGenre(GenreDTO genreDTO) {
        // return genreRepository.save(genreDTO);

        Genre genre = Genre.builder()
            .code(genreDTO.getCode())
            .name(genreDTO.getName())
            .description(genreDTO.getDescription())
            .displayOrder(genreDTO.getDisplayOrder())
            .active(true)
            .build();

        if(genreDTO.getParentGenreId() != null) {
            Genre parentGenre = genreRepository.findById(genreDTO.getParentGenreId())
                .orElseThrow(() -> new RuntimeException("Parent genre not found with id: " + genreDTO.getParentGenreId()));

            genre.setParentGenere(parentGenre);
        }

        Genre savedGenre = genreRepository.save(genre);

        GenreDTO dto = GenreMapper.toDTO(savedGenre);


        return dto;
    }

    @Override
    public List<GenreDTO> getAllGenres() {
        return genreRepository.findAll().stream()
                        .map(GenreMapper::toDTO)
                        .collect(Collectors.toList());
    }

    


}
