package com.example.musicappcompse.data.local.model

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import com.example.musicappcompse.data.local.model.PlaylistSongCrossRef
import com.example.musicappcompse.domain.model.Playlist
import com.example.musicappcompse.domain.model.Song

data class PlaylistWithSongs(
    @Embedded val playlist: Playlist,
    @Relation(
        parentColumn = "playlistId",
        entityColumn = "songId",
        associateBy = Junction(PlaylistSongCrossRef::class)
    )
    val songs: List<Song>
)
