package com.thoen.demoapi.exception;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(value = ApiException.class)
    public ResponseEntity<?> handleException(ApiException exception){
        ErrorResponse errorResponse = new ErrorResponse(exception.getStatus().value(), exception.getMessage());
        return ResponseEntity.status(exception.getStatus()).body(errorResponse);
    }
}
