package com.example.hospitalroomcompose.domain.usecases.medications

import com.example.hospitalroomcompose.data.RepositoryLocal
import com.example.hospitalroomcompose.data.local.model.toMedication
import javax.inject.Inject

class GetAllMedicationsUseCase @Inject constructor(
    private val repositoryLocal: RepositoryLocal
) {
    suspend operator fun invoke () = repositoryLocal.getAllMedications().map { m -> m.toMedication() }
}