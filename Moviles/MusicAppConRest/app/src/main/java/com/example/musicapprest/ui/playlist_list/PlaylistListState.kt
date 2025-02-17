package com.example.musicapprest.ui.playlist_list

import com.example.musicapprest.domain.model.Playlist
import com.example.primeraapp.ui.common.UiEvent

data class PlaylistListState(
    val playlists : List<Playlist> = emptyList(),
    val event : UiEvent? = null,
)
