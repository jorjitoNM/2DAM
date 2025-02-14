package com.example.playlistcompose.data.local.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import com.example.playlistcompose.data.local.model.PlaylistWithSongs
import com.example.playlistcompose.domain.model.Playlist

@Dao
interface PlaylistDao {
    @Transaction
    @Query("SELECT * FROM Playlist")
    fun getPlaylistsWithSongs(): List<PlaylistWithSongs>

    @Query("SELECT * FROM Playlist")
    fun getAll(): List<Playlist>
}