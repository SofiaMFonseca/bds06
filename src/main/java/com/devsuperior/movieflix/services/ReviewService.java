package com.devsuperior.movieflix.services;

import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.movieflix.dto.ReviewDTO;
import com.devsuperior.movieflix.entities.Movie;
import com.devsuperior.movieflix.entities.Review;
import com.devsuperior.movieflix.repositories.MovieRepository;
import com.devsuperior.movieflix.repositories.ReviewRepository;
import com.devsuperior.movieflix.services.exceptions.ResourceNotFoundException;

@Service
public class ReviewService {
	
	@Autowired
	private ReviewRepository repository;
	
	@Autowired
	private MovieRepository movieRepository;
	
	@Transactional(readOnly = true)
	public List<ReviewDTO> findByMovie(Long movieId) {
		try {
			Movie movie = movieRepository.getOne(movieId);
			List<Review> list = repository.findByMovie(movie);
			return list.stream().map(x -> new ReviewDTO(x)).collect(Collectors.toList());
		}
		catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException("Id not found " + movieId);
		}
	}

}
