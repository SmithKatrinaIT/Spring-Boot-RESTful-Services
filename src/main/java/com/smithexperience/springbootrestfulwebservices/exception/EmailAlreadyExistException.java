package com.smithexperience.springbootrestfulwebservices.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class EmailAlreadyExistException extends RuntimeException {

    private String message;

    public EmailAlreadyExistException(String message){
        super(message);
    }
}
