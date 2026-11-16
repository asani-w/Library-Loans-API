package com.example.library_loans.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice 
public class GlobalExceptionHandler {
    @ExceptionHandler(BadInputException.class)
    public ResponseEntity<String>   handleBadInputException(BadInputException ex){
        return ResponseEntity.status(400).body(ex.getMessage());
    }

    @ExceptionHandler(MissingInputException.class)
    public ResponseEntity<String> handleMissingInputException(MissingInputException ex){
        return ResponseEntity.status(404).body(ex.getMessage());
    }

    
}
