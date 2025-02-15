package com.example.musicappcompse.data.local.dao

import androidx.room.Dao
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import com.example.musicappcompse.data.local.model.PlaylistWithSongs
import com.example.musicappcompse.domain.model.Playlist

@Dao
interface PlaylistDao {
    @Transaction
    @Query("SELECT * FROM Playlist")
    fun getPlaylistsWithSongs(): List<PlaylistWithSongs>

    @Query("SELECT * FROM Playlist")
    fun getAll(): List<Playlist>

    @Query("SELECT * FROM Playlist WHERE playlistId == :playlistId")
    fun getPlaylist (playlistId : Int) : PlaylistWithSongs

    @Update(onConflict = OnConflictStrategy.ABORT)
    fun update(playlist: Playlist): Int
}