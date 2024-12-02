package com.example.examenjorgenovillo.ui.jugadores_list

interface JugadoresListEvents {
    data class getJugadores (val equipoId : Int) : JugadoresListEvents
    data object eventDone : JugadoresListEvents
}