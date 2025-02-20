package org.springrest.ui.model;

import lombok.Data;
import org.springrest.domain.model.Song;

import java.util.List;

@Data
public class PlaylistUI {
    private int playlistId;
    private String playlistName;
    private List<Song> songs;
    private String owner;
}
