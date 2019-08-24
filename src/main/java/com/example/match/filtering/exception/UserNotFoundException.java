package com.example.match.filtering.exception;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(){

    }
    public UserNotFoundException(String user){
        super("User Not found " + user);
    }
}
