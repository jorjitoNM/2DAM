package com.example.musiccompose.data.local.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import com.example.musiccompose.data.local.model.PlaylistWithSongs
import com.example.musiccompose.domain.model.Playlist

@Dao
interface PlaylistDao {
    @Transaction
    @Query("SELECT * FROM Playlist")
    fun getPlaylistsWithSongs(): List<PlaylistWithSongs>

    @Query("SELECT * FROM Playlist")
    fun getAll(): List<Playlist>
}