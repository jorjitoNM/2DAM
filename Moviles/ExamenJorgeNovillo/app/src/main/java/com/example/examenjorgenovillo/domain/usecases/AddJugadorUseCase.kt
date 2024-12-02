package com.example.examenjorgenovillo.domain.usecases

import com.example.examenjorgenovillo.data.remote.JugadoresRepository
import com.example.examenjorgenovillo.domain.model.Jugador
import com.example.examenjorgenovillo.domain.model.validateJugador
import javax.inject.Inject

class AddJugadorUseCase @Inject constructor(private val jugadoresRepository: JugadoresRepository) {
    suspend operator fun invoke(equipoId: Int, jugador: Jugador) =
        jugador.validateJugador(jugador).then { jugadoresRepository.getJugador(equipoId, jugador) }
}