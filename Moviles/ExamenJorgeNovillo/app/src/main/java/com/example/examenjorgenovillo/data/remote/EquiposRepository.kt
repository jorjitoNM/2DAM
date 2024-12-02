package com.example.examenjorgenovillo.data.remote

import com.example.examenjorgenovillo.data.remote.datasource.EquiposDataSource
import javax.inject.Inject

class EquiposRepository @Inject constructor(
    private val equiposDataSource: EquiposDataSource,
) {
    suspend fun getEquipos () =
        equiposDataSource.getEquipos()

}