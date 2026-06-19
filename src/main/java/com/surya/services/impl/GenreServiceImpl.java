package com.surya.services.impl;

import org.springframework.stereotype.Service;

import com.surya.modal.Genre;
import com.surya.repository.GenreRepository;
import com.surya.services.GenreService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class GenreServiceImpl implements GenreService{

    
    private final GenreRepository genreRepository;

    @Override
    public Genre creatGenre(Genre genre) {
        return genreRepository.save(genre);
    }

}
