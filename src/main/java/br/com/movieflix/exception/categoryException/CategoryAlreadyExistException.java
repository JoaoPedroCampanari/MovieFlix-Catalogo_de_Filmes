package br.com.movieflix.exception.categoryException;

import br.com.movieflix.exception.GlobalExceptionAbstract;
import org.springframework.http.HttpStatus;

public class CategoryAlreadyExistException extends GlobalExceptionAbstract {

    private static final String MSG = "Category name already exist";
    private static final HttpStatus STATUS = HttpStatus.BAD_REQUEST;

    public CategoryAlreadyExistException() {
        super(MSG, STATUS);
    }

    public CategoryAlreadyExistException(String customMessage) {
        super(customMessage, STATUS);
    }
}
