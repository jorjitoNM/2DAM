package org.springrest.ui;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springrest.common.Constantes;
import org.springrest.domain.model.Playlist;
import org.springrest.domain.services.PlaylistService;

import java.util.List;

@RestController
@RequestMapping(Constantes.PLAYLISTS)
public class PlaylistController {
    private final PlaylistService playlistService;

    public PlaylistController(PlaylistService playlistService) {
        this.playlistService = playlistService;
    }

    @GetMapping(Constantes.GET_ALL)
    public List<Playlist> getAll() {
        return playlistService.getAll();
    }

    @PostMapping(Constantes.GET_URL)
    public ResponseEntity<Playlist> get(@RequestParam String dishId) {
        if (dishId != null) {
            Playlist p = playlistService.get(Integer.parseInt(dishId));
            if (p != null) {
                return ResponseEntity.ok(p);
            }
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping(Constantes.DELETE_URL + "/{"+ Constantes.PATH_ID + "}")
    public ResponseEntity<Boolean> delete(@PathVariable(Constantes.PATH_ID) String id) {
        playlistService.delete(Integer.valueOf(id));
            return ResponseEntity.status(HttpServletResponse.SC_NO_CONTENT).build();
//        else
//            return ResponseEntity.status(HttpServletResponse.SC_INTERNAL_SERVER_ERROR).build();
    }

    @PutMapping(Constantes.UPDATE_URL)
    public ResponseEntity<Playlist> update(@RequestBody Playlist playlist) {
        return ResponseEntity.ok(playlistService.update(playlist));
    }
}
