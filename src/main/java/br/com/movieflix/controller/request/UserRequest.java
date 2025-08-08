package br.com.movieflix.controller.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

@Builder
public record UserRequest(@NotBlank(message = "O campo name é obrigatório")
                          String name,
                          @NotBlank(message = "O campo email deve ser preenchido. ")
                          @Email(message = "O campo email deve ter formato de email. ")
                          String email,
                          @NotBlank(message = "O campo password é obrigatório")
                          String password) {
}
