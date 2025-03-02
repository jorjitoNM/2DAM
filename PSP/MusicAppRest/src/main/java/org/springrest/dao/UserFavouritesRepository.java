package org.springrest.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springrest.domain.model.UserFavourite;

public interface UserFavouritesRepository extends JpaRepository<UserFavourite, Integer> {
}
