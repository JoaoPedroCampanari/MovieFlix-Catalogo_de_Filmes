package br.com.movieflix.service;

import br.com.movieflix.entity.Category;
import br.com.movieflix.entity.Movie;
import br.com.movieflix.entity.Streaming;
import br.com.movieflix.repository.MovieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

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

    @Transactional(readOnly = true)
    public List<Category> findCategories(List<Category> categories){
        return categories.stream()
                .map(category -> categoryService.findByIdByListFromMovie(category.getId()))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .toList();
    }

    @Transactional(readOnly = true)
    public List<Streaming> findStreamings(List<Streaming> streamings){
        return streamings.stream()
                .map(streaming -> streamingService.findByIdFromMovieList(streaming.getId()))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .toList();
    }

    @Transactional(readOnly = true)
    public Optional<Movie> findById(UUID id) {
        return movieRepository.findById(id);
    }

    @Transactional
    public void deleteById(UUID id) {
        movieRepository.deleteById(id);
    }

    @Transactional
    public Optional<Movie> update(UUID id, Movie updateMovie){
        Optional<Movie> optMovie = movieRepository.findById(id);

        if (optMovie.isPresent()){

            List<Category> categories = this.findCategories(updateMovie.getCategories());
            List<Streaming> streamings = this.findStreamings(updateMovie.getStreamings());

            Movie movie = optMovie.get();
            movie.setTitle(updateMovie.getTitle());
            movie.setDescription(updateMovie.getDescription());
            movie.setRating(updateMovie.getRating());
            movie.setReleaseDate(updateMovie.getReleaseDate());
            movie.getCategories().clear();
            movie.getCategories().addAll(categories);
            movie.getStreamings().clear();
            movie.getStreamings().addAll(streamings);

            movieRepository.save(movie);

            return Optional.of(movie);
        }

        return Optional.empty();
    }

    @Transactional(readOnly = true)
    public List<Movie> findByCategory(UUID categoryId){
        return movieRepository.findMovieByCategories(List.of(Category.builder().id(categoryId).build()));
    }
}
