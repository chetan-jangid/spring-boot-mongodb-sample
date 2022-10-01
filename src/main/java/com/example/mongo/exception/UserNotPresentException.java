package com.example.mongo.exception;

public class UserNotPresentException extends RuntimeException {
    public UserNotPresentException(String message) {
        super(message);
    }
}
