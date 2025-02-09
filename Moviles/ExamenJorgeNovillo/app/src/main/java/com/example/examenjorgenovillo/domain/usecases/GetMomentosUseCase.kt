package com.example.examenjorgenovillo.domain.usecases

import com.example.examenjorgenovillo.data.remote.MomentosRepository
import javax.inject.Inject

class GetMomentosUseCase @Inject constructor(private val momentosRepository: MomentosRepository) {
    suspend operator fun invoke (equipoId : Int) =
        momentosRepository.getMomentos(equipoId)
}