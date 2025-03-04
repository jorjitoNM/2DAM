package org.example.backend.domain.errors;

public class NoTienesCosasException extends ApiError{
    public NoTienesCosasException(String message) {
        super(404, message);
    }
}
