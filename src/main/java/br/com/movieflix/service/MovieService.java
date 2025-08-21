package br.com.movieflix.service;

import br.com.movieflix.controller.request.MovieRequest;
import br.com.movieflix.controller.response.MovieResponse;
import br.com.movieflix.entity.Category;
import br.com.movieflix.entity.Movie;
import br.com.movieflix.entity.Streaming;
import br.com.movieflix.mapper.MovieMapper;
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
    public MovieResponse save(MovieRequest movieRequest){
        Movie movie = MovieMapper.toMovie(movieRequest);

        List<Category> categories = movieRequest.catogories()
                .stream()
                .map(uuid -> Category.builder().id(uuid).build()).toList();
        List<Streaming> streamings = movieRequest.streamings()
                .stream()
                .map(uuid -> Streaming.builder().id(uuid).build()).toList();

        movie.setCategories(this.findCategories(categories));
        movie.setStreamings(this.findStreamings(streamings));
        Movie save = movieRepository.save(movie);
        return MovieMapper.toMovieResponse(save);
    }

    @Transactional(readOnly = true)
    public List<MovieResponse> findAllMovies() {
        return movieRepository.findAll()
                .stream()
                .map(MovieMapper::toMovieResponse)
                .toList();
    }

    @Transactional(readOnly = true)
    public MovieResponse findById(UUID id) {
        return movieRepository.findById(id)
                .map(MovieMapper::toMovieResponse)
                .orElse(null);
    }

    @Transactional
    public void deleteById(UUID id) {
        movieRepository.deleteById(id);
    }

    @Transactional(readOnly = true)
    public List<MovieResponse> findByCategory(UUID categoryId){
        return movieRepository.findMovieByCategories(List.of(Category.builder().id(categoryId).build()))
                .stream()
                .map(MovieMapper::toMovieResponse)
                .toList();
    }

    @Transactional
    public MovieResponse update(UUID id, MovieRequest movieRequest){
        Optional<Movie> optMovie = movieRepository.findById(id);
        Movie updateMovie = MovieMapper.toMovie(movieRequest);

        if (optMovie.isPresent()){
            List<Category> categories = this.findCategories(updateMovie.getCategories());
            List<Streaming> streamings = this.findStreamings(updateMovie.getStreamings());

            Movie movie = optMovie.get();
            if (updateMovie.getTitle() != null){
                movie.setTitle(updateMovie.getTitle());
            }
            if (updateMovie.getDescription() != null){
                movie.setDescription(updateMovie.getDescription());
            }
            if (updateMovie.getRating() != null){
                movie.setRating(updateMovie.getRating());
            }
            if (updateMovie.getReleaseDate() != null){
                movie.setReleaseDate(updateMovie.getReleaseDate());
            }
            if (!categories.isEmpty()){
                movie.getCategories().clear();
                movie.getCategories().addAll(categories);
            }
            if (!streamings.isEmpty()){
                movie.getStreamings().clear();
                movie.getStreamings().addAll(streamings);
            }
            movieRepository.save(movie);
            return MovieMapper.toMovieResponse(movie);
        }

        return null;
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
}
