package br.com.movieflix.controller.response;

import lombok.Builder;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Builder
public record MovieResponse(UUID id,
                            String title,
                            String description,
                            LocalDate releaseDate,
                            double ration,
                            List<CategoryResponse> categoryResponses,
                            List<StreamingResponse> streamingResponses) {
}
