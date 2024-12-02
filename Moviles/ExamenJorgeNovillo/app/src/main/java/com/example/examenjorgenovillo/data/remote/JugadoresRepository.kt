package com.example.examenjorgenovillo.data.remote

import com.example.examenjorgenovillo.data.remote.datasource.JugadoresDataSource
import com.example.examenjorgenovillo.domain.model.Jugador
import javax.inject.Inject

class JugadoresRepository @Inject constructor (
    private val jugadoresDataSource: JugadoresDataSource,
) {
    suspend fun getJugadores (equipoId :Int) =
        jugadoresDataSource.getJugadores(equipoId)

    suspend fun getJugador (equipoId :Int, jugador : Jugador) =
        jugadoresDataSource.getJugador(equipoId,jugador)
}