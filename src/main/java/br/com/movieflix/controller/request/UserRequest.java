package br.com.movieflix.controller.request;

import jakarta.validation.constraints.Email;
import lombok.Builder;

@Builder
public record UserRequest(String name,
                          @Email
                          String email,
                          String password) {
}
