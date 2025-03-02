package org.springrest.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springrest.domain.model.Song;
import org.springrest.domain.model.UserFavourite;

import java.util.List;

public interface UserFavouritesRepository extends JpaRepository<UserFavourite, Integer> {
}
