package br.com.movieflix.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
@Setter
public abstract class GlobalExceptionAbstract extends RuntimeException{

    private final HttpStatus status;

    public GlobalExceptionAbstract(String message, HttpStatus status) {
        super(message);
        this.status = status;
    }
}
