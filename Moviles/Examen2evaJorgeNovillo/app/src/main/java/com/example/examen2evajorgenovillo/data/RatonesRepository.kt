package com.example.examen2evajorgenovillo.data

import com.example.examen2evajorgenovillo.common.NetworkResult
import com.example.examen2evajorgenovillo.data.remote.datasource.RatonesDatasource
import com.example.examen2evajorgenovillo.domain.model.Raton
import javax.inject.Inject

class RatonesRepository @Inject constructor(
    private val ratonesDatasource: RatonesDatasource
) {
    suspend fun getAll () : NetworkResult<List<Raton>> = ratonesDatasource.getAll()
}