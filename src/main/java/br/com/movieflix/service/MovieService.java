package br.com.movieflix.service;

import br.com.movieflix.controller.request.MovieRequest;
import br.com.movieflix.controller.response.MovieResponse;
import br.com.movieflix.entity.Category;
import br.com.movieflix.entity.Movie;
import br.com.movieflix.entity.Streaming;
import br.com.movieflix.repository.MovieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MovieService {

    private final MovieRepository movieRepository;
    private final CategoryService categoryService;
    private final StreamingService streamingService;

    @Transactional
    public Movie save(Movie movie){
        movie.setCategories(this.findCategories(movie.getCategories()));
        movie.setStreamings(this.findStreamings(movie.getStreamings()));

        return movieRepository.save(movie);
    }

    @Transactional(readOnly = true)
    public List<Movie> findAllMovies() {
        return movieRepository.findAll();
    }

    public List<Category> findCategories(List<Category> categories){
        return categories.stream()
                .map(category -> categoryService.findbyId(category.getId()))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .toList();
    }

    public List<Streaming> findStreamings(List<Streaming> streamings){
        return streamings.stream()
                .map(streaming -> streamingService.getById(streaming.getId()))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .toList();
    }
}
