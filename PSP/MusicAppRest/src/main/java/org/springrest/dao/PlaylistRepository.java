package org.springrest.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springrest.domain.model.Playlist;

@Repository
public interface PlaylistRepository extends JpaRepository<Playlist, Integer> {
}
