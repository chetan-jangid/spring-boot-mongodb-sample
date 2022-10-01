package com.example.mongo.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler({UserNotPresentException.class})
    public ResponseEntity<ExceptionResponse> handle(UserNotPresentException e) {
        return ResponseEntity.badRequest().body(new ExceptionResponse(e.getMessage(), LocalDateTime.now().toString()));
    }
}
