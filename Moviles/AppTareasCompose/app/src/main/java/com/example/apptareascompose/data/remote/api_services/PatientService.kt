package com.example.apptareascompose.data.remote.api_services

import com.example.apptareascompose.data.remote.model.PatientRemote
import retrofit2.Response
import retrofit2.http.GET

interface PatientService {

    @GET("patients")
    suspend fun getAllPatients () :Response<List<PatientRemote>>

}