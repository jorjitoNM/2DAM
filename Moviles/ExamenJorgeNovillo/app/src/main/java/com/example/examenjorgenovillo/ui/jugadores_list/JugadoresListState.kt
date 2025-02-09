package com.example.examenjorgenovillo.ui.jugadores_list

import com.example.examenjorgenovillo.domain.model.Jugador
import com.example.examenjorgenovillo.ui.common.UiEvent

data class JugadoresListState(
    val jugadores : List<Jugador> = emptyList(),
    val event : UiEvent? = null,
)
