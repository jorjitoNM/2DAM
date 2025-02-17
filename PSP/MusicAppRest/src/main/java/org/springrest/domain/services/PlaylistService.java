package org.springrest.domain.services;


import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springrest.common.Constantes;
import org.springrest.dao.PlaylistRepository;
import org.springrest.domain.errors.ForeignKeyException;
import org.springrest.domain.model.Playlist;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PlaylistService {

    private final PlaylistRepository repository;


    public List<Playlist> getAll () {
        return repository.findAll();
    }

    public void delete(Integer dishId) {
        repository.deleteById(dishId);
    }

    public Playlist update (Playlist p) {
        return repository.save(p);
    }

    public Playlist get (Integer playlistId) {
        return repository.getReferenceById(playlistId);
    }
}
