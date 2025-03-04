package org.example.backend.domain.errors;

import java.time.LocalDate;

public abstract class  ApiError extends RuntimeException {

    protected int code;
    protected String message;
    protected LocalDate time;

    public ApiError(int code, String message) {
        this.code = code;
        this.message = message;
        this.time = LocalDate.now();
    }
}
