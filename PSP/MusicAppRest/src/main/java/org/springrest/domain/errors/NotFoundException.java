package org.springrest.domain.errors;

public class NotFoundException extends ApiError {

    public NotFoundException(String message) {
        super(400,message);
    }
}
