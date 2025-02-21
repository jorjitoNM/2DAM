package com.example.examen2evajorgenovillo.data.remote.datasource

import com.example.examen2evajorgenovillo.common.NetworkResult
import com.example.examen2evajorgenovillo.data.remote.api_services.RatonesService
import com.example.examen2evajorgenovillo.domain.model.Raton
import javax.inject.Inject

class RatonesDatasource @Inject constructor(
    private val ratonesService: RatonesService,
) : BaseApiResponse() {
    suspend fun getAll () : NetworkResult<List<Raton>> = safeApiCall {
        ratonesService.getAll()
    }
}