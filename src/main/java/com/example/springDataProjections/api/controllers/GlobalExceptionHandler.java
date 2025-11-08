package com.example.springDataProjections.api.controllers;

import com.example.springDataProjections.api.exeption.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BadRequestException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleBadRequestException(BadRequestException exception) {
        return new ErrorResponse(
                HttpStatus.BAD_REQUEST.value(),
                exception.getMessage());
    }

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse handleNotFoundException(NotFoundException exception) {
        return new ErrorResponse(HttpStatus.NOT_FOUND.value()
                , exception.getMessage());
    }

    @ExceptionHandler(NoContentException.class)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ErrorResponse handleNoContentException(NoContentException exception) {
        return new ErrorResponse(
                HttpStatus.NO_CONTENT.value(),
                exception.getMessage());
    }

    @ExceptionHandler(GlobalException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorResponse handleException(GlobalException exception) {
        return new ErrorResponse(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                exception.getMessage());
    }
}
