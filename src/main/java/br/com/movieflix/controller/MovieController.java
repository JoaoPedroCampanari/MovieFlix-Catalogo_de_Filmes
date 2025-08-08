package br.com.movieflix.controller;

import br.com.movieflix.controller.request.MovieRequest;
import br.com.movieflix.controller.response.MovieResponse;
import br.com.movieflix.entity.Movie;
import br.com.movieflix.mapper.MovieMapper;
import br.com.movieflix.service.MovieService;
import jakarta.validation.Valid;
import jakarta.websocket.server.PathParam;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

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
    public ResponseEntity<MovieResponse> save(@Valid @RequestBody MovieRequest movieRequest){
        Movie movieSalvo = movieService.save(MovieMapper.toMovie(movieRequest));
        return ResponseEntity.ok(MovieMapper.toMovieResponse(movieSalvo));
    }

    @GetMapping("/{id}")
    public ResponseEntity<MovieResponse> findById(@PathVariable(name = "id") UUID id){
        return movieService.findById(id)
                .map(movie -> ResponseEntity.ok(MovieMapper.toMovieResponse(movie)))
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable(name = "id") UUID id){
        movieService.deleteById(id);

        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<MovieResponse> update(@PathVariable(name = "id") UUID id, @Valid @RequestBody MovieRequest request){
        return movieService.update(id, MovieMapper.toMovie(request))
                .map(movie -> ResponseEntity.ok(MovieMapper.toMovieResponse(movie)))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/search")
    public ResponseEntity<List<MovieResponse>> moviesByCategory(@RequestParam(name = "category") UUID category){
        return ResponseEntity.ok(movieService.findByCategory(category)
                .stream()
                .map(MovieMapper::toMovieResponse)
                .toList());
    }

}
