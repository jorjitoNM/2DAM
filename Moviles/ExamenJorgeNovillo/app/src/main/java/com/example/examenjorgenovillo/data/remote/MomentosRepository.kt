package com.example.examenjorgenovillo.data.remote

import com.example.examenjorgenovillo.data.remote.datasource.MomentosDataSource
import javax.inject.Inject

class MomentosRepository @Inject constructor (
    private val momentosDataSource: MomentosDataSource,
) {
    suspend fun getMomentos (equipoId : Int) =
        momentosDataSource.getMomentos(equipoId)
}