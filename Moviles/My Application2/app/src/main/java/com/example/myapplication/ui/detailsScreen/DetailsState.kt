package com.example.myapplication.ui.detailsScreen

import com.example.myapplication.domain.model.Character
import com.example.myapplication.ui.common.UiEvent

data class DetailsState(
    val character : Character = Character(),
    val event : UiEvent? = null,
)