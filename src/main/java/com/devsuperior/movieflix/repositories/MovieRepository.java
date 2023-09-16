package com.devsuperior.movieflix.repositories;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.devsuperior.movieflix.entities.Movie;

public interface MovieRepository extends JpaRepository<Movie, Long> {
	
	Optional<Movie> findById(Long id);
	
	@Query("SELECT obj FROM Movie obj WHERE obj.genre.id = :genreId "
			+ "ORDER BY obj.title")
	Page<Movie> findByGenre(Long genreId, Pageable pageable);
	
	@Query("SELECT obj FROM Movie obj ORDER BY obj.title")
	Page<Movie> findAll(Pageable pageable);
}
