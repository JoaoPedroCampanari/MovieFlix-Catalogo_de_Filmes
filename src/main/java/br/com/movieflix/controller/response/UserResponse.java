package br.com.movieflix.controller.response;

import jakarta.validation.constraints.Email;
import lombok.Builder;
import java.util.UUID;

@Builder
public record UserResponse(UUID id,
                           String name,
                           @Email
                           String email) {
}
