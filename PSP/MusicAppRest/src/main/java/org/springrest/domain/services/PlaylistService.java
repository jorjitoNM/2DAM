package org.springrest.domain.services;


import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springrest.common.Constantes;
import org.springrest.dao.PlaylistRepository;
import org.springrest.dao.UsersRepository;
import org.springrest.domain.errors.ForeignKeyException;
import org.springrest.domain.errors.NotFoundException;
import org.springrest.domain.model.Playlist;
import org.springrest.ui.model.PlaylistUI;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PlaylistService {

    private final PlaylistRepository repository;
    private final UsersRepository usersRepository;


    public List<Playlist> getAll (String owner) {
        return repository.findAllByOwner_Email(owner);
    }

    public void delete(Integer id) {
        repository.deleteById(id);
    }

    public Playlist update (PlaylistUI p) {
        Playlist playlist = new Playlist(p.getPlaylistId(),p.getPlaylistName(),p.getSongs(),usersRepository.getUserByEmail(p.getOwner()));
        return repository.save(playlist);
    }

    public Playlist get (Integer playlistId) {
        return repository.findById(playlistId).orElse(null);
    }
}
