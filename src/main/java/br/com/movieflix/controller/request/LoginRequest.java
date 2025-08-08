package br.com.movieflix.controller.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

@Builder
public record LoginRequest(@NotBlank(message = "O campo email é obrigatório")
                           String email,
                           @NotBlank(message = "O campo password é obrigatório")
                           String password) {
}
