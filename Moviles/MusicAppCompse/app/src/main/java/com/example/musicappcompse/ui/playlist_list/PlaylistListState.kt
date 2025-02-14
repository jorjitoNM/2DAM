package com.example.playlistcompose.ui.playlist_list

import com.example.musicappcompse.domain.model.Playlist
import com.example.primeraapp.ui.common.UiEvent

data class PlaylistListState(
    val playlists : List<Playlist> = emptyList(),
    val event : UiEvent? = null,
)
