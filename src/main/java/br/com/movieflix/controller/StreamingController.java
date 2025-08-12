package br.com.movieflix.controller;

import br.com.movieflix.controller.request.StreamingRequest;
import br.com.movieflix.controller.response.StreamingResponse;
import br.com.movieflix.entity.Streaming;
import br.com.movieflix.mapper.StreamingMapper;
import br.com.movieflix.service.StreamingService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/movieflix/streaming")
@RequiredArgsConstructor
@SecurityRequirement(name = "bearerAuth")
public class StreamingController {

    private final StreamingService streamingService;

    @GetMapping
    public ResponseEntity<List<StreamingResponse>> getAllStreaming(){
        return ResponseEntity.ok(streamingService.findAllStreaming());
    }

    @GetMapping("/{id}")
    public ResponseEntity<StreamingResponse> getById(@PathVariable(value = "id")UUID id){
        return ResponseEntity.ok().body(streamingService.findById(id));
    }

    @PostMapping
    public ResponseEntity<StreamingResponse> saveStreaming(@Valid @RequestBody StreamingRequest streamingRequest){
        return ResponseEntity.status(HttpStatus.CREATED).body(streamingService.saveStreaming(streamingRequest));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable(value = "id") UUID id){
        streamingService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<StreamingResponse> updateStreaming(@PathVariable(name = "id") UUID id, @RequestBody StreamingRequest streamingRequest){
        return ResponseEntity.ok().body(streamingService.updateStreaming(id, streamingRequest));
    }
}
