package com.example.musicappcompse.data.local.dao

import androidx.room.Dao
import androidx.room.Query
import com.example.musicappcompse.domain.model.Song

@Dao
interface SongsDao {

    @Query("SELECT * FROM song")
    fun getAll() : List<Song>
}