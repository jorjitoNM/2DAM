package com.example.examen2evajorgenovillo.ui.ratones_list

import com.example.examen2evajorgenovillo.domain.model.Raton
import com.example.examen2evajorgenovillo.ui.common.UiEvent

data class RatonesListState(
    val ratones: List<Raton> = emptyList(),
    val event : UiEvent? = null,
    val isLoading : Boolean = false,
)
