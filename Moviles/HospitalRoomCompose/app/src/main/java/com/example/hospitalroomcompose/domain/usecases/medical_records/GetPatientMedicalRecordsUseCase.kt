package com.example.hospitalroomcompose.domain.usecases.medical_records

import com.example.hospitalroomcompose.data.RepositoryLocal
import com.example.hospitalroomcompose.data.local.model.toMedicalRecord
import com.example.hospitalroomcompose.domain.model.MedicalRecord
import javax.inject.Inject

class GetPatientMedicalRecordsUseCase @Inject constructor(
    private val repositoryLocal: RepositoryLocal
) {
    suspend operator fun invoke(id: Int) = repositoryLocal.getPatientMedicalRecords(id).map { pwm -> pwm.medicalRecords.map { m -> m.toMedicalRecord() } }.first()
}