package com.example.examen2evajorgenovillo.domain.usecases.ratones

import com.example.examen2evajorgenovillo.common.NetworkResult
import com.example.examen2evajorgenovillo.data.RatonesRepository
import com.example.examen2evajorgenovillo.domain.model.Raton
import javax.inject.Inject

class GetAllRatonesUseCase @Inject constructor(
    private val ratonesRepository: RatonesRepository,
) {
    suspend fun invoke() : NetworkResult<List<Raton>> = ratonesRepository.getAll()
}