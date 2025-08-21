package br.com.movieflix.exception.streamingException;

import br.com.movieflix.exception.GlobalExceptionAbstract;
import org.springframework.http.HttpStatus;

public class StreamingAlreadyExistException extends GlobalExceptionAbstract {

    private static final String MSG = "Streaming name already exist";
    private static final HttpStatus STATUS = HttpStatus.BAD_REQUEST;

    public StreamingAlreadyExistException() {
        super(MSG, STATUS);
    }

    public StreamingAlreadyExistException(String customMessage) {
        super(customMessage, STATUS);
    }
}
