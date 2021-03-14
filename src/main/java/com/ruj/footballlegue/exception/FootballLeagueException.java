package com.ruj.footballlegue.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class FootballLeagueException {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity handleResourceNotFoundException(final ResourceNotFoundException e){
        Error error1 = new Error("1", e.getMessage());
        return new ResponseEntity(error1, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity exceptionHandlerIllegalArgumentException(final IllegalArgumentException e) {
        Error error1 = new Error("1", e.getMessage());
        return new ResponseEntity(error1, HttpStatus.NOT_FOUND);
    }
}
