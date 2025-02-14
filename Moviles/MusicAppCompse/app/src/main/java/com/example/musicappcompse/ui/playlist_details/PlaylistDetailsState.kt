package com.example.musicappcompse.ui.playlist_details

import com.example.musicappcompse.domain.model.Playlist
import com.example.primeraapp.ui.common.UiEvent

data class PlaylistDetailsState(
    val playlist: Playlist = Playlist(),
    val event : UiEvent? = null,
)
