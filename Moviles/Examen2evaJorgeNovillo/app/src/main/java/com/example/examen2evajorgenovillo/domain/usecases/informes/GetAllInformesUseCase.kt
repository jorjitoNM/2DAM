package com.example.examen2evajorgenovillo.domain.usecases.informes

import com.example.examen2evajorgenovillo.common.NetworkResult
import com.example.examen2evajorgenovillo.data.InformesRepository
import com.example.examen2evajorgenovillo.domain.model.Informe
import javax.inject.Inject

class GetAllInformesUseCase @Inject constructor(
    private val informesRepository: InformesRepository
) {
    fun invoke () : List<Informe> = informesRepository.getAll()
}