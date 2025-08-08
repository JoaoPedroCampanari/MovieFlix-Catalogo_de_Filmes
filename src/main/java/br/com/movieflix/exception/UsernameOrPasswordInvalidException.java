package br.com.movieflix.exception;


public class UsernameOrPasswordInvalidException extends RuntimeException{

    public UsernameOrPasswordInvalidException(String msg){
        super(msg);
    }
}
