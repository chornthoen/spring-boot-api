package com.thoen.demoapi.exception;

import org.springframework.http.HttpStatus;

public class ResourceNotFoundException extends ApiException{
    public ResourceNotFoundException(HttpStatus status, String message) {
        super(status, message);
    }
}
