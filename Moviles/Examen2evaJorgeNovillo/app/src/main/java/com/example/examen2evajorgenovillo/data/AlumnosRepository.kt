package com.example.examen2evajorgenovillo.data

import com.example.examen2evajorgenovillo.common.NetworkResult
import com.example.examen2evajorgenovillo.data.remote.datasource.AlumnosDatasource
import com.example.examen2evajorgenovillo.domain.model.Alumno
import javax.inject.Inject

class AlumnosRepository @Inject constructor(
    private val alumnosDatasource: AlumnosDatasource,
) {
    suspend fun getAll () : NetworkResult<List<Alumno>> = alumnosDatasource.getAll()
}