package com.example.musicapprest.domain.model

data class Playlist(
    val playlistId: Int = 0,
    val playlistName: String = "",
    val songs : List<Song> = emptyList(),
)
