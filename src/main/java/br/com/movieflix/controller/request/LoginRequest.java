package br.com.movieflix.controller.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

@Builder
public record LoginRequest(@Email(message = "O campo email deve ter formato de email. ")
                           @NotBlank(message = "O campo email deve ser preenchido. ")
                           String email,
                           @NotBlank(message = "O campo password é obrigatório")
                           String password) {
}
