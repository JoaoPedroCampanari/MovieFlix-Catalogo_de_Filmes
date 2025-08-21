package br.com.movieflix.mapper;

import br.com.movieflix.controller.request.StreamingRequest;
import br.com.movieflix.controller.response.StreamingResponse;
import br.com.movieflix.entity.Streaming;
import lombok.experimental.UtilityClass;

import java.text.Normalizer;

@UtilityClass
public class StreamingMapper {

    public static Streaming toStreaming(StreamingRequest streamingRequest){
        return Streaming
                .builder()
                .name(normalizarNome(streamingRequest.name()))
                .build();
    }

    public static StreamingResponse toStreamingResponse(Streaming streaming){
        return StreamingResponse
                .builder()
                .id(streaming.getId())
                .name(streaming.getName())
                .build();
    }

    public static String normalizarNome(String nome){
        return Normalizer.normalize(nome, Normalizer.Form.NFD)
                .replaceAll("\\p{InCombiningDiacriticalMarks}+", "") // remove os acentos.
                .replaceAll("\\s+", "")     // remove espaços
                .replaceAll("[^a-z0-9]", "") // remove símbolos
                .toLowerCase();
    }
}
