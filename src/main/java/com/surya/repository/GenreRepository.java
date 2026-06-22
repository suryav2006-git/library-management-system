package com.surya.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.surya.modal.Genre;

public interface GenreRepository extends JpaRepository<Genre, Long>{

    List<Genre> findByActiveTrueOrderByDisplayOrderAsc();

    List<Genre> findByParentGenreIsNullAndActiveTrueOrderByDisplayOrderAsc();

    List<Genre> findByParentGenreIdAndActiveTrueOrderByDisplayOrderAsc(
        Long parentGenreId
    );

    long countByActiveTrue();

    // @Query("select count(b) from Book b where b.genre.id=:genreId")
    // long countBookdByGenre(@Param("genreId") Long genreId);

 	

}
