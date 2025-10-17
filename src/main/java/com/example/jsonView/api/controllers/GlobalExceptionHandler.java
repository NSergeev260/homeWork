package com.example.jsonView.api.controllers;

import com.example.jsonView.api.exeption.UserBadRequestException;
import com.example.jsonView.api.exeption.UserNoContentException;
import com.example.jsonView.api.exeption.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UserBadRequestException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleWalletBadRequestException(UserBadRequestException exception) {
        return new ErrorResponse(
                HttpStatus.BAD_REQUEST.value(),
                exception.getMessage());
    }

    @ExceptionHandler(UserNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse handleWalletNotFoundException(UserNotFoundException exception) {
        return new ErrorResponse(HttpStatus.NOT_FOUND.value()
                ,exception.getMessage());
    }

    @ExceptionHandler(UserNoContentException.class)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ErrorResponse handleWalletNoContentException(UserNoContentException exception) {
        return new ErrorResponse(
                HttpStatus.NO_CONTENT.value(),
                exception.getMessage());
    }
}
