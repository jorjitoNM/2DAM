package org.springrest.ui;

import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springrest.common.Constantes;
import org.springrest.domain.model.Playlist;
import org.springrest.domain.services.PlaylistService;
import org.springrest.security.JWTService;

import java.util.List;

@RestController
@RequestMapping(Constantes.PLAYLISTS_URL)
@RequiredArgsConstructor
public class PlaylistController {
    private final PlaylistService playlistService;
    private final JWTService tokenService;

    @GetMapping(Constantes.GET_ALL)
    public List<Playlist> getAll(@RequestHeader(HttpHeaders.AUTHORIZATION) String token) {
        return playlistService.getAll(tokenService.getEmail(token.split("Bearer ")[1].trim()));
    }

    @PostMapping(Constantes.GET_URL)
    public ResponseEntity<Playlist> get(@RequestBody Integer id) {
        if (id != null) {
            Playlist p = playlistService.get(id);
            if (p != null) {
                return ResponseEntity.ok(p);
            }
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping(Constantes.DELETE_URL + "/{"+ Constantes.PATH_ID + "}")
    public ResponseEntity<Boolean> delete(@PathVariable(Constantes.PATH_ID) Integer id) {
        playlistService.delete(id);
        return ResponseEntity.status(HttpServletResponse.SC_NO_CONTENT).build();
    }

    @PutMapping(Constantes.UPDATE_URL)
    public ResponseEntity<Playlist> update(@RequestBody Playlist playlist) {
        return ResponseEntity.ok(playlistService.update(playlist));
    }
}
