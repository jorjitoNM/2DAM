package org.springrest.ui;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springrest.common.Constantes;
import org.springrest.domain.model.Song;
import org.springrest.domain.services.SongsService;

import java.util.List;

@RestController
@RequestMapping(Constantes.SONGS_URL)
@RequiredArgsConstructor
public class SongsController {
    private final SongsService service;

    @GetMapping(Constantes.GET_ALL)
    public List<Song> getAll () {
        return service.getAll();
    }

    @GetMapping(Constantes.FAVOURITE_SONG + "/{"+ Constantes.PATH_ID + "}")
    public Song addFavouriteSong (@PathVariable(Constantes.PATH_ID) int id) {
        return service.addFavouriteSong();
    }
}
