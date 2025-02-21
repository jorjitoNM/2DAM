package com.example.examen2evajorgenovillo.data.remote.api_services

import com.example.examen2evajorgenovillo.domain.model.Alumno
import retrofit2.Response
import retrofit2.http.GET

interface AlumnosService {
    @GET("alumnos")
    suspend fun getAll () : Response<List<Alumno>>
}