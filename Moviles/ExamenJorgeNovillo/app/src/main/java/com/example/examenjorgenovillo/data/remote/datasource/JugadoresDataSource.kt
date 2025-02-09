package com.example.examenjorgenovillo.data.remote.datasource

import com.example.examenjorgenovillo.data.remote.api_service.ApiService
import com.example.examenjorgenovillo.domain.model.Jugador
import javax.inject.Inject

class JugadoresDataSource @Inject constructor(
    private val apiService: ApiService,
) : BaseApiResponse() {
    suspend fun getJugadores(equipoId :Int) =
        safeApiCall { apiService.getJugadores(equipoId) }

    suspend fun getJugador (equipoId :Int, jugador : Jugador) =
        safeApiCall { apiService.addJugador(equipoId,jugador) }
}