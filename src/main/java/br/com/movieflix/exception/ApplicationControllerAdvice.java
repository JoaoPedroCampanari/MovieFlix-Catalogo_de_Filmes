package br.com.movieflix.exception;

import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;
import java.util.stream.Collectors;

@RestControllerAdvice
public class ApplicationControllerAdvice{

    @ExceptionHandler(UsernameOrPasswordInvalidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleUsernameOrPasswordInvalidException(UsernameOrPasswordInvalidException exception){
        return exception.getMessage();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, String> handleArgumentNotValidException(MethodArgumentNotValidException exception){
         return exception.getBindingResult()
                 .getFieldErrors()
                 .stream()
                 .collect(Collectors.toMap(FieldError::getField, FieldError::getDefaultMessage, (mensagemAntiga, mensagemNova) -> mensagemAntiga + " " + mensagemNova));
    }
}
