package com.example.apptareascompose.data.remote.datasource

import com.example.apptareascompose.data.remote.api_services.DoctorsService
import javax.inject.Inject

class DoctorsDataSource @Inject constructor(
    private val doctorsService: DoctorsService
)  : BaseApiResponse() {
    suspend fun getAllDoctors() = safeApiCall {
        doctorsService.getAllDoctors()
    }
}