package br.com.movieflix.service;

import br.com.movieflix.entity.Streaming;
import br.com.movieflix.repository.StreamingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class StreamingService {

    private final StreamingRepository streamingRepository;

    @Transactional(readOnly = true)
    public List<Streaming> findAllStreaming() {
        return streamingRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Optional<Streaming> findById(UUID id){
        return streamingRepository.findById(id);
    }

    @Transactional
    public Streaming saveStreaming(Streaming streaming){
        return streamingRepository.save(streaming);
    }

    public void deleteById(UUID id) {
        streamingRepository.deleteById(id);
    }
}
