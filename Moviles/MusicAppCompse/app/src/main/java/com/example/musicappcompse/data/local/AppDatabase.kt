package com.example.musicappcompse.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.musicappcompse.data.local.dao.PlaylistDao
import com.example.musicappcompse.data.local.dao.SongsDao
import com.example.musicappcompse.data.local.dao.UsersDao
import com.example.musicappcompse.data.local.model.PlaylistSongCrossRef
import com.example.musicappcompse.domain.model.Playlist
import com.example.musicappcompse.domain.model.Song
import com.example.musicappcompse.domain.model.User

@Database(entities = [Song::class, Playlist::class, PlaylistSongCrossRef::class, User::class],
    version = 5,
    exportSchema = true)
abstract class AppDatabase : RoomDatabase() {
    abstract fun playlistDao() : PlaylistDao
    abstract fun songsDao() : SongsDao
    abstract fun usersDao() : UsersDao
}