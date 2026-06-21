package com.surya.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.surya.exception.GenreException;
import com.surya.payload.dto.GenreDTO;
import com.surya.payload.response.ApiResponse;
import com.surya.services.GenreService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/genres") 
public class GenreController {

    private final GenreService genreService;

    @PostMapping("/create") 
    public ResponseEntity<GenreDTO> addGenre(@RequestBody GenreDTO genre) {
        GenreDTO createdGenre = genreService.createGenre(genre);
        return ResponseEntity.ok(createdGenre);
        
    }

    @GetMapping() 
    public ResponseEntity<?> getAllGenres() {
        List<GenreDTO> genres = genreService.getAllGenres();
        return ResponseEntity.ok(genres);
        
    }

    @GetMapping("/{genreId}") 
    public ResponseEntity<?> getGenreById(
            @RequestParam("genreId") Long genreId
        ) throws GenreException {
        
        GenreDTO genres = genreService.getGenreById(genreId);
        return ResponseEntity.ok(genres);
        
    }

    @PutMapping("/{genreId}") 
    public ResponseEntity<?> updateGenre(
            @RequestParam("genreId") Long genreId,
            @RequestBody GenreDTO genre
        ) throws GenreException {
        
        GenreDTO genres = genreService.updateGenre(genreId, genre);
        return ResponseEntity.ok(genres);
        
    }

    @DeleteMapping("/{genreId}") 
    public ResponseEntity<?> deleteGenre(
            @RequestParam("genreId") Long genreId
        ) throws GenreException {
        
        genreService.deleteGenre(genreId);
        ApiResponse response = new ApiResponse("Genre deleted - Soft Delete", true);
        return ResponseEntity.ok(response);
        
    }

    @DeleteMapping("/{genreId}/hard") 
    public ResponseEntity<?> hardDeleteGenre(
            @RequestParam("genreId") Long genreId
        ) throws GenreException {
        
        genreService.hardDeleteGenre(genreId);
        ApiResponse response = new ApiResponse("Genre deleted - Hard Delete", true);
        return ResponseEntity.ok(response);
        
    }

}

