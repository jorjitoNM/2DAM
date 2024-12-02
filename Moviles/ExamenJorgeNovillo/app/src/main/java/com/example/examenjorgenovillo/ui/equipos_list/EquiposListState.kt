package com.example.examenjorgenovillo.ui.equipos_list

import com.example.examenjorgenovillo.ui.common.UiEvent
import com.example.examenjorgenovillo.domain.model.Equipo

data class EquiposListState (
    val equipos : List<Equipo> = emptyList(),
    val event : UiEvent? = null,
)
