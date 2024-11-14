package com.example.myapplication.data.remote.model

import com.example.myapplication.domain.model.Song

data class SongRemote(
    val artist : String,
    val duration : Int,
    val explicit : Boolean,
    val name : String,
    val albumImage : String,
)

fun SongRemote.toSong() = Song(
    artist  = artist,
    duration =  duration,
    explicit = explicit,
    name = name,
    albumImage = albumImage,
    )