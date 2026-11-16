package com.example.library_loans.exception;

public class MissingInputException extends RuntimeException {
    public MissingInputException(String message){
        super(message);
    }
}
