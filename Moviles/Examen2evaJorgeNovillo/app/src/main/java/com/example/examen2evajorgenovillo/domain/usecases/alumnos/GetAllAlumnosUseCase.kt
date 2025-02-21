package com.example.examen2evajorgenovillo.domain.usecases.alumnos

import com.example.examen2evajorgenovillo.common.NetworkResult
import com.example.examen2evajorgenovillo.data.AlumnosRepository
import com.example.examen2evajorgenovillo.domain.model.Alumno
import javax.inject.Inject

class GetAllAlumnosUseCase @Inject constructor(
    private val alumnosRepository : AlumnosRepository,
) {
    suspend fun invoke () : NetworkResult<List<Alumno>> = alumnosRepository.getAll()
}