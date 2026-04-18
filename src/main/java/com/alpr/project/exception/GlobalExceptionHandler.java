package com.alpr.project.exception;

import java.net.http.HttpRequest;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.apache.catalina.connector.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GlobalExceptionHandler {
    
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleResourceNotFound(ResourceNotFoundException exception){
        ErrorResponse response = ErrorResponse.builder()
        .status(HttpStatus.NOT_FOUND.value())
        .message(exception.getMessage())
        .timestamp(LocalDateTime.now())
        .errors(null)
        .build();

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ErrorResponse> handleBadRequest(BadRequestException exception){
        ErrorResponse response = ErrorResponse.builder()
        .status(HttpStatus.BAD_REQUEST.value())
        .message(exception.getMessage())
        .timestamp(LocalDateTime.now())
        .errors(null)
        .build();

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @ExceptionHandler(UnauthorizedActionException.class)
    public ResponseEntity<ErrorResponse> handleUnauthorizedAction(UnauthorizedActionException exception){
        ErrorResponse response = ErrorResponse.builder()
        .status(HttpStatus.UNAUTHORIZED.value())
        .message(exception.getMessage())
        .timestamp(LocalDateTime.now())
        .errors(null)
        .build();

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidationError(MethodArgumentNotValidException expception){
        Map<String,String> validationErrors = new HashMap<>();

        expception.getBindingResult().getFieldErrors().forEach(error ->
            validationErrors.put(error.getField(),error.getDefaultMessage())
        );

        ErrorResponse response = ErrorResponse.builder()
        .status(HttpStatus.BAD_REQUEST.value())
        .message("Validation Hatası")
        .timestamp(LocalDateTime.now())
        .errors(validationErrors)
        .build();

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGeneralException(Exception exception){
        ErrorResponse response = ErrorResponse.builder()
        .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
        .message("Beklenmeyen Bir Hata Oluştu")
        .timestamp(LocalDateTime.now())
        .errors(null)
        .build();

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }

}
