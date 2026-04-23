package com.alpr.project.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import com.alpr.project.dto.ApiResponse;

@RestController
public class GlobalExceptionHandler {
    
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiResponse<Object>> handleResourceNotFound(ResourceNotFoundException exception){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse<>(false, null, exception.getMessage()));
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ApiResponse<Object>> handleBadRequest(BadRequestException exception){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse<>(false, null, exception.getMessage()));
    }

    @ExceptionHandler(UnauthorizedActionException.class)
    public ResponseEntity<ApiResponse<Object>> handleUnauthorizedAction(UnauthorizedActionException exception){
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new ApiResponse<>(false, null, exception.getMessage()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<Map<String,String>>> handleValidationError(MethodArgumentNotValidException expception){
        Map<String,String> validationErrors = new HashMap<>();

        expception.getBindingResult().getFieldErrors().forEach(error ->
            validationErrors.put(error.getField(),error.getDefaultMessage())
        );

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse<>(false, validationErrors, "Validation hatası"));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<Object>> handleGeneralException(Exception exception){
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse<>(false, null, "Beklenmeyen bir hata oluştu"));
    }

}
