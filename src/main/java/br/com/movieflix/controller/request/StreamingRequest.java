package br.com.movieflix.controller.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;

@Builder
public record StreamingRequest(
        @NotBlank(message = "Nome do streaming é obrigatório. ")
        String name) {
}
