package com.hospitalcrud.ui.error;

import com.hospitalcrud.domain.error.DataBaseError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(DataBaseError.class)
    public ResponseEntity<DataBaseError> handleForeignKetException(DataBaseError e) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(e);
    }
}
