package com.example.musiccompose.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Song(
    @PrimaryKey(autoGenerate = true)
    val songId: Long,
    val songName: String,
    val artist: String
)