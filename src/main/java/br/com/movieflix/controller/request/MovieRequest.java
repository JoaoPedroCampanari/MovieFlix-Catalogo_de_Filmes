package br.com.movieflix.controller.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Builder
public record MovieRequest(
                           @NotEmpty(message = "Titulo do filme é obrigatório. ")
                           String title,
                           String description,
                           @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
                           LocalDate releaseDate,
                           double rating,
                           List<UUID> catogories,
                           List<UUID> streamings
) {
}
