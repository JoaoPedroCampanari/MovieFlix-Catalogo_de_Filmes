package br.com.movieflix.controller.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
public record CategoryRequest(
        @NotEmpty(message = "Nome da categoria é obrigatório.")
        String name) {
}
