package com.example.myapplication.ui.mainScreen

import com.example.myapplication.domain.model.Song

data class MainState(
    val songs: List<Song> = emptyList(),
)