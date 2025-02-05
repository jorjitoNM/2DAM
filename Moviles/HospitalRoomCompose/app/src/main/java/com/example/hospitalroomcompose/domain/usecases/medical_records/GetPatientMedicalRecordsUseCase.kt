package com.example.hospitalroomcompose.domain.usecases.medical_records

import com.example.hospitalroomcompose.data.RepositoryLocal
import javax.inject.Inject

class GetPatientMedicalRecordsUseCase @Inject constructor(
    private val repositoryLocal: RepositoryLocal
) {
    suspend fun invoke(id: Int) = repositoryLocal.getPatientMedicalRecords(id)
}