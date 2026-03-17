package org.example.gymmanagement_api.exception;

public class MethodArgumentNotValidException extends RuntimeException{

    public MethodArgumentNotValidException(String message) {
        super(message);
    }
}
