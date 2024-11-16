package com.example.myapplication.data.remote.model

import com.example.myapplication.domain.model.Song

data class SongRemote(
    val id :String,
    val name: String,
    val artist: ArrayList<Artists>,
    val duration: Int,
    val explicit: Boolean,
    val albumImage: String,
)

fun SongRemote.toSong() = Song(
    id = id,
    name = name,
    artist = artist.map { a -> a.name }.toList(),
    duration = duration,
    explicit = explicit,
    albumImage = albumImage,
    )