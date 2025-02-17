package org.springrest.domain.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springrest.dao.SongsRepository;
import org.springrest.domain.model.Song;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SongsService {
    private final SongsRepository repository;

    public List<Song> getAll () {
        return repository.findAll();
    }

    public Song addFavouriteSong() {
        return null;
    }
}
