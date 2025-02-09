package com.example.examenjorgenovillo.domain.model

import com.example.examenjorgenovillo.data.remote.NetworkResult
import com.example.examenjorgenovillo.utilities.Constantes

data class Jugador(
    val nombre : String = "juan",
    val apellido : String = "perez",
    val dorsal : Int = 1,
)
fun Jugador.validateJugador(jugador: Jugador): NetworkResult<Jugador> =
    if (jugador.nombre.isBlank() || jugador.apellido.isBlank() || jugador.dorsal > 99 || jugador.dorsal < 0)
        NetworkResult.Error(Constantes.INVALID_PLAYER)
    else
        NetworkResult.Success(jugador)