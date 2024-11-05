package com.hospitalcrud.ui.error;

import com.hospitalcrud.domain.error.DataBaseError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

public class GlobalExceptionHandler extends RuntimeException {
    @ExceptionHandler(DataBaseError.class)
    public ResponseEntity<DataBaseError> handleForeignKetException(DataBaseError e) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(e);
    }
}
