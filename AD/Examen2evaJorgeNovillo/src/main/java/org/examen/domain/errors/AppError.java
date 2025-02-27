package org.examen.domain.errors;

public class AppError extends RuntimeException {
    public AppError(String message) {
        super(message);
    }
}
