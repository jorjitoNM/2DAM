package com.example.hospitalroomcompose.domain.usecases.medical_records

import com.example.hospitalroomcompose.data.MedicalRecordsRepository
import com.example.hospitalroomcompose.data.local.model.toMedicalRecord
import javax.inject.Inject

class GetPatientMedicalRecordsUseCase @Inject constructor(
    private val medicalRecordsRepository: MedicalRecordsRepository
) {
    suspend operator fun invoke(id: Int) = medicalRecordsRepository.getPatientMedicalRecords(id)
        .map { pwm -> pwm.medicalRecords.map { m -> m.toMedicalRecord() } }.first()
}