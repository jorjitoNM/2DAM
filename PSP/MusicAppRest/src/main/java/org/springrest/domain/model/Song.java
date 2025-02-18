package org.springrest.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    @JsonIgnore
    private List<Playlist> playlist;
}
