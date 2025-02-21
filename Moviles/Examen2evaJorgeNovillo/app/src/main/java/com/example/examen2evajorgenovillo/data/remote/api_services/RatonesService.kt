package com.example.examen2evajorgenovillo.data.remote.api_services

import com.example.examen2evajorgenovillo.domain.model.Raton
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PUT

interface RatonesService {

    @GET("ratones")
    suspend fun getAll () : Response<List<Raton>>

    @PUT("ratones")
    suspend fun add (@Body raton : Raton) : Response<Raton>
}