package br.com.movieflix.controller;

import br.com.movieflix.controller.request.MovieRequest;
import br.com.movieflix.controller.response.MovieResponse;
import br.com.movieflix.entity.Movie;
import br.com.movieflix.mapper.MovieMapper;
import br.com.movieflix.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movieflix/movie")
@RequiredArgsConstructor
public class MovieController {

    private final MovieService movieService;

    @GetMapping
    public ResponseEntity<List<MovieResponse>> getAllMovies(){
        List<MovieResponse> movieResponses = movieService
                .findAllMovies()
                .stream()
                .map(MovieMapper::toMovieResponse)
                .toList();
        return ResponseEntity.ok(movieResponses);
    }

    @PostMapping
    public ResponseEntity<MovieResponse> save(@RequestBody MovieRequest movieRequest){
        Movie movieSalvo = movieService.save(MovieMapper.toMovie(movieRequest));
        return ResponseEntity.ok(MovieMapper.toMovieResponse(movieSalvo));
    }
}
