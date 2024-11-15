package com.example.myapplication.data.remote.model

import com.example.myapplication.domain.model.Song

data class SongRemote(
    val name: String,
    val artist: ArrayList<Artists>,
    val duration: Int,
    val explicit: Boolean,
    val albumImage: String,
)

fun SongRemote.toSong() = Song(
    name = name,
    artist = artist,
    duration = duration,
    explicit = explicit,
    albumImage = albumImage,
    )