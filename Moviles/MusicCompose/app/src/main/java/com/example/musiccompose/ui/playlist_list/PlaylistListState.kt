package com.example.musiccompose.ui.playlist_list

import com.example.musiccompose.domain.model.Playlist
import com.example.musiccompose.ui.common.UiEvent

data class PlaylistListState(
    val playlists : List<Playlist> = emptyList(),
    val event : UiEvent? = null,
)
