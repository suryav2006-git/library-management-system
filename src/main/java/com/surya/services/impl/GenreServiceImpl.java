package com.surya.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.surya.exception.GenreException;
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
    private final GenreMapper genreMapper;

    @Override
    public GenreDTO createGenre(GenreDTO genreDTO) {
        
        Genre genre = genreMapper.toEntity(genreDTO);

        Genre savedGenre = genreRepository.save(genre);

        return genreMapper.toDTO(savedGenre);
    }

    @Override
    public List<GenreDTO> getAllGenres() {
        return genreRepository.findAll().stream()
                        .map(genreMapper::toDTO)
                        .collect(Collectors.toList());
    }

    @Override
    public GenreDTO getGenreById(Long genraId) throws GenreException{
        Genre genre = genreRepository.findById(genraId).orElseThrow(
            () -> new GenreException("genre Not Found")
        );
        return genreMapper.toDTO(genre);
    }

    @Override
    public GenreDTO updateGenre(Long genreId, GenreDTO genreDTO) throws GenreException {
        Genre existingGenre = genreRepository.findById(genreId).orElseThrow(
            () -> new GenreException("genre Not Found")
        );

        genreMapper.updateEntityFromDTO(genreDTO, existingGenre);
        
        Genre updatedGenre = genreRepository.save(existingGenre);

        return genreMapper.toDTO(updatedGenre);
    }

    @Override
    public void deleteGenre(Long genreId) throws GenreException {
        Genre existingGenre = genreRepository.findById(genreId).orElseThrow(
            () -> new GenreException("genre Not Found")
        );

        existingGenre.setActive(false);
        genreRepository.save(existingGenre);
    }
    @Override
    public void hardDeleteGenre(Long genreId) throws GenreException {
        Genre existingGenre = genreRepository.findById(genreId).orElseThrow(
            () -> new GenreException("genre Not Found")
        );

        genreRepository.delete(existingGenre);
    }

    @Override
    public List<GenreDTO> getAllActiveGenresWithSubGenres() {
        List<Genre> topLevelGenres = genreRepository.findByParentGenreIsNullAndActiveTrueOrderByDisplayOrderAsc();

        return genreMapper.toDTOList(topLevelGenres);    
    }

    @Override
    public List<GenreDTO> getTopLevelGenres() {
        List<Genre> topLevelGenres = genreRepository.findByParentGenreIsNullAndActiveTrueOrderByDisplayOrderAsc();

        return genreMapper.toDTOList(topLevelGenres);    
    }

    @Override
    public long getTotalActiveGenres() {
        return genreRepository.countByActiveTrue();    
    }

    @Override
    public long getBookCountByGenre(Long genreId) {
        return 0;    
    }

    


}
