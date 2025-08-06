package br.com.movieflix.controller.request;

import lombok.Builder;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Builder
public record MovieRequest(String title,
                           String description,
                           LocalDate releaseDate,
                           double rating,
                           List<UUID> catogories,
                           List<UUID> streamings
) {
}
