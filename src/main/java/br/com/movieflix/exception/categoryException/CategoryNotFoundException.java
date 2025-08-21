package br.com.movieflix.exception.categoryException;

import br.com.movieflix.exception.GlobalExceptionAbstract;
import org.springframework.http.HttpStatus;


public class CategoryNotFoundException extends GlobalExceptionAbstract {

    private static final String MSG = "Category not Found";
    private static final HttpStatus STATUS = HttpStatus.NOT_FOUND;


    public CategoryNotFoundException() {
        super(MSG, STATUS);
    }

    public CategoryNotFoundException(String customMessage){
        super(customMessage, STATUS);
    }


}
