package com.example.springDataJdbc.api.exeption;

public class NoContentException extends RuntimeException {
    public NoContentException(String message) {
        super(message);
    }
}
