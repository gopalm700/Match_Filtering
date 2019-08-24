package com.example.match.filtering.controller.advice;

import com.example.match.filtering.exception.UserNotFoundException;
import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.UNAUTHORIZED;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingRequestHeaderException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ResponseStatus(BAD_REQUEST)
    @ExceptionHandler({MethodArgumentNotValidException.class, HttpMessageNotReadableException.class, MissingRequestHeaderException.class})
    public String handleBadRequest() {
        return "Invalid Request";
    }


    @ResponseStatus(INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public String handleException(Exception e) {
        e.printStackTrace();
        return "Some error occured. Contact gopalm700@gmail.com";
    }


    @ResponseStatus(UNAUTHORIZED)
    @ExceptionHandler(UserNotFoundException.class)
    public String handleUserNotFoundException() {
        return "user not logged in.";
    }
}
