package org.example.backend.domain.errors;

public class NoEsTuCosaException extends ApiError{

    public NoEsTuCosaException(int code, String message) {
        super(code, message);
    }
}
