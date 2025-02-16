package org.springrest.domain.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "songs")
public class Song {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "song_id")
    private int songId;
    @Column(name = "name")
    private String songName;
    @Column
    private String artist;
    @ManyToMany(mappedBy = "songs")
    private List<Playlist> playlist;
}
