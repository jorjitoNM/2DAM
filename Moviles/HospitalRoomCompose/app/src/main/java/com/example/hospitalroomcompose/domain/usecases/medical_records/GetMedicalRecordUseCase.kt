package com.example.hospitalroomcompose.domain.usecases.medical_records

import com.example.hospitalroomcompose.data.RepositoryLocal
import com.example.hospitalroomcompose.data.local.model.toMedicalRecord
import javax.inject.Inject

class GetMedicalRecordUseCase @Inject constructor(
    private val repositoryLocal: RepositoryLocal
) {
    suspend operator fun invoke (id : Int) = repositoryLocal.getMedicalRecord(id).toMedicalRecord()
}