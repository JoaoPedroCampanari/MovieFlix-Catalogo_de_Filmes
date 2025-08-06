package br.com.movieflix.controller.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Builder
public record MovieResponse(UUID id,
                            String title,
                            String description,
                            @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
                            LocalDate releaseDate,
                            double ration,
                            List<CategoryResponse> categoryResponses,
                            List<StreamingResponse> streamingResponses) {
}
