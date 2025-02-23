package org.springrest.domain.errors;

public class ForeignKeyException extends ApiError {
    public ForeignKeyException(String message) {
        super(400,message);
    }
}
