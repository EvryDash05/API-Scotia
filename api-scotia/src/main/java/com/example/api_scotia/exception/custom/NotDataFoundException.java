package com.example.api_scotia.exception.custom;

public class NotDataFoundException extends RuntimeException {

    public NotDataFoundException() {
        super();
    }

    public NotDataFoundException(String message) {
        super(message);
    }
}
