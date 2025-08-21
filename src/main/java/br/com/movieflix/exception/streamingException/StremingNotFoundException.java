package br.com.movieflix.exception.streamingException;

import br.com.movieflix.exception.GlobalExceptionAbstract;
import org.springframework.http.HttpStatus;

public class StremingNotFoundException extends GlobalExceptionAbstract {

    private static final String MSG = "Streaming not found";
    private static final HttpStatus STATUS = HttpStatus.NOT_FOUND;

    public StremingNotFoundException() {
        super(MSG, STATUS);
    }

    public StremingNotFoundException(String customMessage) {
        super(customMessage, STATUS);
    }
}
