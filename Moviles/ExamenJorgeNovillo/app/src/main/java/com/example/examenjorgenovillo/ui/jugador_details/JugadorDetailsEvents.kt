package com.example.examenjorgenovillo.ui.jugador_details

import com.example.examenjorgenovillo.domain.model.Jugador

interface JugadorDetailsEvents {
    data class addJugador (val equipoId : Int, val jugador : Jugador) : JugadorDetailsEvents
    data object eventDone : JugadorDetailsEvents
}