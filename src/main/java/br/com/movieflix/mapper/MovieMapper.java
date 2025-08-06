package br.com.movieflix.mapper;

import br.com.movieflix.controller.request.MovieRequest;
import br.com.movieflix.controller.response.CategoryResponse;
import br.com.movieflix.controller.response.MovieResponse;
import br.com.movieflix.controller.response.StreamingResponse;
import br.com.movieflix.entity.Category;
import br.com.movieflix.entity.Movie;
import br.com.movieflix.entity.Streaming;
import lombok.experimental.UtilityClass;

import java.util.List;

@UtilityClass
public class MovieMapper {

    public static Movie toMovie(MovieRequest movieRequest){

        List<Category> categories = movieRequest.catogories().stream().map(categoryId -> Category.builder().id(categoryId).build()).toList();
        List<Streaming> streamings = movieRequest.streamings().stream().map(streamingId -> Streaming.builder().id(streamingId).build()).toList();


        return Movie.builder()
                .title(movieRequest.title())
                .rating(movieRequest.rating())
                .description(movieRequest.description())
                .releaseDate(movieRequest.releaseDate())
                .categories(categories)
                .streamings(streamings)
                .build();
    }

    public static MovieResponse toMovieResponse(Movie movie) {
        List<CategoryResponse> categoryResponses = movie.getCategories().stream().map(CategoryMapper::toCategoryResponde).toList();
        List<StreamingResponse> streamingResponses = movie.getStreamings().stream().map(StreamingMapper::toStreamingResponse).toList();

        return MovieResponse.builder()
                .id(movie.getId())
                .title(movie.getTitle())
                .ration(movie.getRating())
                .description(movie.getDescription())
                .releaseDate(movie.getReleaseDate())
                .categoryResponses(categoryResponses)
                .streamingResponses(streamingResponses)
                .build();
    }
}
