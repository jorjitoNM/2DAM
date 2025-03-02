package org.springrest.domain.services;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springrest.common.Constantes;
import org.springrest.dao.SongsRepository;
import org.springrest.dao.UserFavouritesRepository;
import org.springrest.dao.UsersRepository;
import org.springrest.domain.errors.SongAlreadyAddedToFavourites;
import org.springrest.domain.errors.NotFoundException;
import org.springrest.domain.model.Song;
import org.springrest.domain.model.User;
import org.springrest.domain.model.UserFavourite;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SongsService {
    private final SongsRepository repository;
    private final UsersRepository usersRepository;
    private final UserFavouritesRepository userFavouritesRepository;

    public List<Song> getAll() {
        return repository.findAll();
    }

    @Transactional
    public Song addToFavourite(String email, Integer id) {
        Song song = repository.findById(id).orElseThrow(() -> new NotFoundException(Constantes.SONG_NOT_FOUND));
        User user = usersRepository.findUserByEmail(email).orElseThrow(() -> new UsernameNotFoundException(Constantes.USER_NOT_FOUND));
        if (user.getFavourites().stream().noneMatch(s -> s.getSong().getSongId() == song.getSongId())) {
            throw new SongAlreadyAddedToFavourites(Constantes.SONG_ALREADY_ADDED_TO_FAVOURITES);
        }
        userFavouritesRepository.save(new UserFavourite(song,user));
        return song;
    }


    public List<Song> getFavourites (String email) {
        return repository.getUserFavourites(email);
    }
}
