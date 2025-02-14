package com.example.musiccompose.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.musiccompose.data.local.dao.PlaylistDao
import com.example.musiccompose.data.local.dao.UserDao
import com.example.musiccompose.data.local.model.PlaylistSongCrossRef
import com.example.musiccompose.domain.model.Playlist
import com.example.musiccompose.domain.model.Song
import com.example.musiccompose.domain.model.User

@Database(entities = [Song::class, Playlist::class, PlaylistSongCrossRef::class, User::class],
    version = 3,
    exportSchema = true)
abstract class AppDatabase : RoomDatabase() {
    abstract fun playlistDao() : PlaylistDao
    abstract fun userDao() : UserDao
}