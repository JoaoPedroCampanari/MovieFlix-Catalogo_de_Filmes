package br.com.movieflix.controller.response;

import lombok.Builder;

import java.util.UUID;

@Builder
public record StreamingResponse(UUID id, String name) {
}
