package br.com.movieflix.config;

import lombok.Builder;

import java.util.UUID;

@Builder
public record JWTUserData(String name, String email) {
}
