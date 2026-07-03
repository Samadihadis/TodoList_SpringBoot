package com.samadihadis.todolist_springboot.exception;


import com.samadihadis.todolist_springboot.exception.task.TaskNotFoundException;
import com.samadihadis.todolist_springboot.exception.taskList.TaskListNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

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
}
