package com.ungspp1.gadminbackend.exceptions;

import org.springframework.http.HttpStatus;

public class EngineException extends Exception {
    private HttpStatus status;

    public EngineException(String message) {
        super(message);
    }

    public EngineException(String message, Throwable ex) {
        super(message, ex);
    }

    public EngineException(String message, HttpStatus status) {
        super(message);
        this.status = status;
    }


    public String getStatus() {
        return status.toString();
    }
}
