package com.example.hospitalroomcompose.domain.usecases.medications

import com.example.hospitalroomcompose.data.MedicationsRepository
import com.example.hospitalroomcompose.data.local.model.toMedication
import javax.inject.Inject

class GetAllMedicationsUseCase @Inject constructor(
    private val medicationsRepository: MedicationsRepository
) {
    suspend operator fun invoke () = medicationsRepository.getAllMedications().map { m -> m.toMedication() }
}