package com.example.musicapprest.ui.songs_list

import com.example.musicapprest.domain.model.Song
import com.example.primeraapp.ui.common.UiEvent

data class SongsListState(
    val songs : List<Song> = emptyList(),
    val event: UiEvent? = null,
)
