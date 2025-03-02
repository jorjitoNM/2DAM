package org.springrest.domain.errors;

public class SongAlreadyAddedToFavourites extends ApiError {

    public SongAlreadyAddedToFavourites(String message) {
        super(400,message);
    }
}
