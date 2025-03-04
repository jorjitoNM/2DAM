package org.example.backend.domain.errors;

public class NoEsTuCosa extends ApiError{
    public NoEsTuCosa(String message) {
        super(403, message);
    }
}
