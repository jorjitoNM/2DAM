package com.example.examen2evajorgenovillo.data.remote.datasource

import com.example.examen2evajorgenovillo.common.NetworkResult
import com.example.examen2evajorgenovillo.data.remote.api_services.AlumnosService
import com.example.examen2evajorgenovillo.domain.model.Alumno
import javax.inject.Inject

class AlumnosDatasource @Inject constructor(
    private val alumnosService: AlumnosService,
) : BaseApiResponse() {

    suspend fun getAll () : NetworkResult<List<Alumno>> = safeApiCall {
        alumnosService.getAll()
    }
}