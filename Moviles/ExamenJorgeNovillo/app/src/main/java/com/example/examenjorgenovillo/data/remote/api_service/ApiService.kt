package com.example.examenjorgenovillo.data.remote.api_service

import com.example.examenjorgenovillo.domain.model.Equipo
import com.example.examenjorgenovillo.domain.model.Jugador
import com.example.examenjorgenovillo.domain.model.Momento
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PUT
import retrofit2.http.Path

interface ApiService {

    @GET("equipos/{id}/jugadores")
    suspend fun getJugadores (@Path("id") idEquipo : Int) : Response<List<Jugador>>

    @GET("equipos")
    suspend fun getEquipos () : Response<List<Equipo>>

    @PUT("equipos/{equipo}/jugadores")
    suspend fun addJugador (@Path("equipo") euquipoId : Int, @Body jugador : Jugador) : Response<Jugador>

    @GET("equipos/{id}/momentos")
    suspend fun getMomentos (@Path("id") equipoId : Int) : Response<List<Momento>>
}