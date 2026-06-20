package com.surya.services;

import java.util.List;

import com.surya.payload.dto.GenreDTO;

public interface GenreService {

    GenreDTO createGenre(GenreDTO genreDTO);


    List<GenreDTO> getAllGenres();


}
