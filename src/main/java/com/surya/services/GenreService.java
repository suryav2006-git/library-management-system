package com.surya.services;

import java.util.List;

import com.surya.exception.GenreException;

// import org.springframework.boot.data.autoconfigure.web.DataWebProperties.Pageable;
// import org.springframework.data.domain.Page;

import com.surya.payload.dto.GenreDTO;

public interface GenreService {

    GenreDTO createGenre(GenreDTO genreDTO);


    List<GenreDTO> getAllGenres();

    GenreDTO getGenreById(Long genraId) throws GenreException;

    GenreDTO updateGenre(Long genreId, GenreDTO genreDTO) throws GenreException;

    void deleteGenre(Long genreId) throws GenreException;

    void hardDeleteGenre(Long genreId);

    List<GenreDTO> getAllActiveGenresWithSubGenres();

    List<GenreDTO> getTopLevelGenres();

    // Page<GenreDTO> searchGenres(String searchTerm, Pageable pageable);

    long getTotalActiveGenres();

    long getBookCountByGenre(Long genreId);


}
