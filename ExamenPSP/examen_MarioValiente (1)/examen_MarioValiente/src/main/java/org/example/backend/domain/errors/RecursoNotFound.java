package org.example.backend.domain.errors;

public class RecursoNotFound extends RuntimeException {
    public RecursoNotFound(String message) {
        super(message);
    }
}
