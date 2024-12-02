package com.example.examenjorgenovillo.domain.usecases

import com.example.examenjorgenovillo.data.remote.EquiposRepository
import javax.inject.Inject

class GetEquiposUseCase @Inject constructor(private val equiposRepository: EquiposRepository) {
    suspend operator fun invoke () =
        equiposRepository.getEquipos()
}