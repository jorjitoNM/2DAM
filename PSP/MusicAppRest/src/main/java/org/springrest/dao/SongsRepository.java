package org.springrest.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springrest.domain.model.Song;

@Repository
public interface SongsRepository extends JpaRepository<Song, Integer> {
}
