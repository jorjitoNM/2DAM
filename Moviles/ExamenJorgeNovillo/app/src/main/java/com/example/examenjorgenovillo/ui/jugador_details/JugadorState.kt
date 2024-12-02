package com.example.examenjorgenovillo.ui.jugador_details

import com.example.examenjorgenovillo.domain.model.Jugador
import com.example.examenjorgenovillo.ui.common.UiEvent

data class JugadorState(
    val jugador : Jugador = Jugador(),
    val event : UiEvent ? = null,
)
