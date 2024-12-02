package com.example.examenjorgenovillo.data.remote.datasource

import com.example.examenjorgenovillo.data.remote.api_service.ApiService
import javax.inject.Inject

class MomentosDataSource @Inject constructor(
    private val apiService: ApiService,
) : BaseApiResponse() {

    suspend fun getMomentos (equipoId : Int) =
        safeApiCall { apiService.getMomentos(equipoId) }
}