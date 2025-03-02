package org.springrest.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springrest.domain.model.Song;

import java.util.List;

@Repository
public interface SongsRepository extends JpaRepository<Song, Integer> {
    @Query("SELECT s FROM Song s join UserFavourite uf on s.songId = uf.song.songId where uf.user.email = :email")
    List<Song> getUserFavourites(String email);
}
