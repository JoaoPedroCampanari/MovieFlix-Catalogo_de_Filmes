package br.com.movieflix.service;

import br.com.movieflix.controller.request.StreamingRequest;
import br.com.movieflix.controller.response.StreamingResponse;
import br.com.movieflix.entity.Category;
import br.com.movieflix.entity.Streaming;
import br.com.movieflix.mapper.StreamingMapper;
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
    public List<StreamingResponse> findAllStreaming() {
        return streamingRepository.findAll()
                .stream()
                .map(StreamingMapper::toStreamingResponse)
                .toList();
    }

    @Transactional(readOnly = true)
    public StreamingResponse findById(UUID id){
        return streamingRepository.findById(id)
                .map(StreamingMapper::toStreamingResponse)
                .orElse(null);
    }

    @Transactional
    public StreamingResponse saveStreaming(StreamingRequest streamingRequest){
        Streaming streaming = streamingRepository.save(StreamingMapper.toStreaming(streamingRequest));
        return StreamingMapper.toStreamingResponse(streamingRepository.save(streaming));
    }

    @Transactional
    public void deleteById(UUID id) {
        streamingRepository.deleteById(id);
    }

    @Transactional(readOnly = true)
    public Optional<Streaming> findByIdFromMovieList(UUID id){
        return streamingRepository.findById(id);
    }

    public StreamingResponse updateStreaming(UUID id, StreamingRequest streamingRequest) {
        Optional<Streaming> streaming = streamingRepository.findById(id);

        if (streaming.isPresent()){
            if (streamingRequest.name() != null){
                streaming.get().setName(streamingRequest.name());
            }
            return StreamingMapper.toStreamingResponse(streamingRepository.save(streaming.get()));
        }

        return null;
    }
}
