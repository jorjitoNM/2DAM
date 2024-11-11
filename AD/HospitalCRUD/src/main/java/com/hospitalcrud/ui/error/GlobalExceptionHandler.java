package com.hospitalcrud.ui.error;

import com.hospitalcrud.domain.error.DataBaseError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.handler.ResponseStatusExceptionHandler;

@Component
@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseStatusExceptionHandler {
    @ExceptionHandler(DataBaseError.class)
    public ResponseEntity<DataBaseError> handleForeignKetException(DataBaseError e) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(e);
    }
}
