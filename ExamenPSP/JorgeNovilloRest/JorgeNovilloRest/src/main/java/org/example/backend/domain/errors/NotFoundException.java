package org.example.backend.domain.errors;

public class NotFoundException extends ApiError{
    public NotFoundException(String message) {
        super(404, message);
    }
}
