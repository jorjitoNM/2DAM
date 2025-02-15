package com.example.musicappcompse.ui.playlist_details

import com.example.musicappcompse.data.local.model.PlaylistWithSongs
import com.example.primeraapp.ui.common.UiEvent

data class PlaylistDetailsState(
    val playlist: PlaylistWithSongs = PlaylistWithSongs(),
    val userName : String = "Jorge",
    val event : UiEvent? = null,
)
