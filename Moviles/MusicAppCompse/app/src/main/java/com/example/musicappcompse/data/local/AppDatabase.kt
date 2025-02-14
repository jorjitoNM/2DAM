package com.example.musicappcompse.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.musicappcompse.data.local.dao.PlaylistDao
import com.example.musicappcompse.data.local.model.PlaylistSongCrossRef
import com.example.musicappcompse.domain.model.Playlist
import com.example.musicappcompse.domain.model.Song

@Database(entities = [Song::class, Playlist::class, PlaylistSongCrossRef::class],
    version = 3,
    exportSchema = true)
abstract class AppDatabase : RoomDatabase() {
    abstract fun playlistDao() : PlaylistDao
}