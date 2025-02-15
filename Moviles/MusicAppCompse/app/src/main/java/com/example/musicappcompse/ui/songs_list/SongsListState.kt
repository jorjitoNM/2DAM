package com.example.musicappcompse.ui.songs_list

import com.example.musicappcompse.domain.model.Song
import com.example.primeraapp.ui.common.UiEvent

data class SongsListState(
    val songs : List<Song> = emptyList(),
    val event: UiEvent? = null,
)
