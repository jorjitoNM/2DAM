package com.example.myapplication.ui.detailsScreen

import com.example.myapplication.domain.model.Book
import com.example.myapplication.domain.model.Song
import com.example.myapplication.ui.common.UiEvent

data class DetailsState(
    val song : Song = Song(),
    val event : UiEvent? = null,
)