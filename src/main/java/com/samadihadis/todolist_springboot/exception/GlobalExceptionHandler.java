package com.samadihadis.todolist_springboot.exception;


import com.samadihadis.todolist_springboot.exception.task.TaskNotFoundException;
import com.samadihadis.todolist_springboot.exception.taskList.TaskListNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    //Task Exception
    @ExceptionHandler(TaskNotFoundException.class)
    public ResponseEntity<ErrorResponse> handlerTaskNotFound(TaskNotFoundException ex) {

        ErrorResponse error = new ErrorResponse(
                ex.getMessage(),
                HttpStatus.NOT_FOUND.value(),
                "Not Found"
        );

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    //Task List Exception
    @ExceptionHandler(TaskListNotFoundException.class)
    public ResponseEntity<ErrorResponse> handlerTaskListNotFound(TaskListNotFoundException ex) {

        ErrorResponse error = new ErrorResponse(
                ex.getMessage(),
                HttpStatus.NOT_FOUND.value(),
                "Not Found"
        );

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    //Catch-all
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ErrorResponse> handleGeneralException(Exception ex) {

        ErrorResponse error = new ErrorResponse(
                "خطای داخلی سرور: ",
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                "Internal Server Error"
        );

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
    }

    //Validation
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidationException(MethodArgumentNotValidException ex) {

        Map<String, String> errors = new HashMap<>();

        ex.getBindingResult().getAllErrors().forEach(error -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });

        ErrorResponse errorResponse = new ErrorResponse(
                "Validation Failed",
                HttpStatus.BAD_REQUEST.value(),
                "Bad Request",
                errors
        );

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }
}
