package br.com.movieflix.controller.response;

import lombok.Builder;

@Builder
public record LoginResponse(String email,
                            String token) {
}
