package org.springrest.domain.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "playlists")
public class Playlist {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "playlist_id")
    private int playlistId;
    @Column(name = "name")
    private String playlistName;
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "playlist_songs",
            joinColumns = { @JoinColumn(name = "playlist_id") },
            inverseJoinColumns = { @JoinColumn(name = "song_id") }
    )
    private List<Song> songs;
    @ManyToOne
    @JoinColumn(name = "owner", referencedColumnName = "email")
    private User owner;
}
