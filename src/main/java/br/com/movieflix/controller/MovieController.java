package br.com.movieflix.controller;

import br.com.movieflix.controller.request.MovieRequest;
import br.com.movieflix.controller.response.MovieResponse;
import br.com.movieflix.entity.Movie;
import br.com.movieflix.mapper.MovieMapper;
import br.com.movieflix.service.MovieService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
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
@SecurityRequirement(name = "bearerAuth")
public class MovieController {

    private final MovieService movieService;

    @GetMapping
    public ResponseEntity<List<MovieResponse>> getAllMovies(){
        return ResponseEntity.ok(movieService.findAllMovies());
    }

    @PostMapping
    public ResponseEntity<MovieResponse> save(@Valid @RequestBody MovieRequest movieRequest){
        Movie movieSalvo = movieService.save(MovieMapper.toMovie(movieRequest));
        return ResponseEntity.ok(MovieMapper.toMovieResponse(movieSalvo));
    }

    @GetMapping("/{id}")
    public ResponseEntity<MovieResponse> findById(@PathVariable(name = "id") UUID id){
        return ResponseEntity.ok().body(movieService.findById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable(name = "id") UUID id){
        movieService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<MovieResponse> update(@PathVariable(name = "id") UUID id, @Valid @RequestBody MovieRequest request){
        return ResponseEntity.ok().body(movieService.update(id, request));
    }

    @GetMapping("/search")
    public ResponseEntity<List<MovieResponse>> moviesByCategory(@RequestParam(name = "category") UUID category){
        return ResponseEntity.ok().body(movieService.findByCategory(category));
    }

}
