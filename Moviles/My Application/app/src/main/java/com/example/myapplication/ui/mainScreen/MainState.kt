package com.example.myapplication.ui.mainScreen

import com.example.myapplication.domain.model.Song
import com.example.myapplication.ui.common.UiEvent

data class MainState(
    val songs: List<Song> = emptyList(),
    val appEvent : UiEvent? = null,
)