package com.example.api_scotia.exception.custom;

public class ForbiddenException extends RuntimeException {

    private final int code;

    public ForbiddenException(int code, String message) {
        super(message);
        this.code = code;
    }

    public int getCode() {
        return code;
    }

}
