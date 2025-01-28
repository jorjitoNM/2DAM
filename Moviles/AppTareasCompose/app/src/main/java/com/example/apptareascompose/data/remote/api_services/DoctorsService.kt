package com.example.apptareascompose.data.remote.api_services

import com.example.apptareascompose.domain.model.Doctor
import retrofit2.Response
import retrofit2.http.GET

interface DoctorsService {
    @GET("doctors")
    suspend fun getAllDoctors () : Response<List<Doctor>>
}