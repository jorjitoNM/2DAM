package com.example.playlistcompose.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.playlistcompose.data.local.dao.PlaylistDao
import com.example.playlistcompose.data.local.dao.UserDao
import com.example.playlistcompose.data.local.model.PlaylistSongCrossRef
import com.example.playlistcompose.domain.model.Playlist
import com.example.playlistcompose.domain.model.Song
import com.example.playlistcompose.domain.model.User

@Database(entities = [Song::class,Playlist::class,PlaylistSongCrossRef::class,User::class],
    version = 2,
    exportSchema = true)
abstract class AppDatabase : RoomDatabase() {
    abstract fun playlistDao() : PlaylistDao
    abstract fun userDao() : UserDao
}