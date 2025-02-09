package com.example.examenjorgenovillo.data.remote.datasource

import com.example.examenjorgenovillo.data.remote.NetworkResult
import com.example.examenjorgenovillo.data.remote.api_service.ApiService
import com.example.examenjorgenovillo.domain.model.Equipo
import javax.inject.Inject

class EquiposDataSource @Inject constructor(
    private val apiService : ApiService,
) : BaseApiResponse() {

    suspend fun getEquipos () : NetworkResult<List<Equipo>> {
        return safeApiCall { apiService.getEquipos() }
    }
}