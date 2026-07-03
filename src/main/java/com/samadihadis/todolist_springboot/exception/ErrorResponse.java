package com.samadihadis.todolist_springboot.exception;


import lombok.Getter;

import java.time.LocalDateTime;
import java.util.Map;

@Getter
public class ErrorResponse {
    private String message;
    private int status;
    private String error;
    private LocalDateTime timestamp;
    private Map<String , String> errors;

    public ErrorResponse(String message, int status, String error) {
        this.message = message;
        this.status = status;
        this.error = error;
        this.timestamp = LocalDateTime.now();
    }

    public ErrorResponse(String message, int status, String error, Map<String, String> errors) {
        this.message = message;
        this.status = status;
        this.error = error;
        this.timestamp = LocalDateTime.now();
        this.errors = errors;
    }
}
