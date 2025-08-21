package br.com.movieflix.service;

import br.com.movieflix.controller.request.StreamingRequest;
import br.com.movieflix.controller.response.StreamingResponse;
import br.com.movieflix.entity.Streaming;
import br.com.movieflix.exception.streamingException.StreamingAlreadyExistException;
import br.com.movieflix.exception.streamingException.StremingNotFoundException;
import br.com.movieflix.mapper.StreamingMapper;
import br.com.movieflix.repository.StreamingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
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
                .orElseThrow(StremingNotFoundException::new);
    }

    @Transactional
    public StreamingResponse saveStreaming(StreamingRequest streamingRequest){
        if (streamingRepository.existsStreamingByNameIgnoreCase(streamingRequest.name())){
            throw new StreamingAlreadyExistException();
        }



        Streaming streaming = streamingRepository.save(StreamingMapper.toStreaming(streamingRequest));
        return StreamingMapper.toStreamingResponse(streamingRepository.save(streaming));
    }

    @Transactional
    public void deleteById(UUID id) {
        if (!streamingRepository.existsById(id)){
            throw new StremingNotFoundException();
        }
        streamingRepository.deleteById(id);
    }

    @Transactional(readOnly = true)
    public Streaming findByIdFromMovieList(UUID id){
        return streamingRepository.findById(id).orElseThrow(StremingNotFoundException::new);
    }

    public StreamingResponse updateStreaming(UUID id, StreamingRequest streamingRequest) {
        Streaming streaming = streamingRepository.findById(id).orElseThrow(StremingNotFoundException::new);
        if (streamingRepository.existsStreamingByNameIgnoreCase(streamingRequest.name())){
            throw new StreamingAlreadyExistException();
        }
        streaming.setName(streamingRequest.name());
        return StreamingMapper.toStreamingResponse(streamingRepository.save(streaming));
    }
}
