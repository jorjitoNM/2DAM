package com.example.hospitalroomcompose.domain.usecases.medications

import com.example.hospitalroomcompose.data.RepositoryLocal

class GetAllMedicationsUseCase @Inject constructor(
    private val repositoryLocal: RepositoryLocal
) {
    suspend operator fun invoke () = repositoryLocal.getAllMedications()
}