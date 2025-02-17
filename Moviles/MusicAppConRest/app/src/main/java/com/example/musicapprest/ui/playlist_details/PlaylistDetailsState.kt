package com.example.musicapprest.ui.playlist_details

import com.example.musicapprest.domain.model.Playlist
import com.example.primeraapp.ui.common.UiEvent

data class PlaylistDetailsState(
    val playlist: Playlist = Playlist(),
    val userName : String = "Jorge",
    val event : UiEvent? = null,
)
