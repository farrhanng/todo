package com.sctp.todo.exceptions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    // generic exception handler
    @ExceptionHandler
    public ResponseEntity<String> handleException(Exception e) {
        logger.error("An unexpected error occurred: ", e);
        return new ResponseEntity<>("An error occurred: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    // handler for TaskNotFoundException
    @ExceptionHandler(TaskNotFoundException.class)
    public ResponseEntity<Object> handleTaskNotFoundException(TaskNotFoundException e) {
        logger.error("Task not found: ", e);
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("message", "Task not found.");
        return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
    }

    // handler for MethodArgumentNotValidException
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        // Collect error details from field errors
        List<String> errors = ex.getBindingResult().getFieldErrors().stream().map(fieldError -> fieldError.getField() + ": " + fieldError.getDefaultMessage()).collect(Collectors.toList());
        logger.error("Method argument not valid: {}", errors);

        // prepare the response body with detailed information
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("status", HttpStatus.BAD_REQUEST.value());
        body.put("message", "Validation error");
        body.put("errors", errors);

        // return the detailed error response
        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }
}