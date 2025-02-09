package com.example.hospitalroomcompose.domain.usecases.medical_records

import com.example.hospitalroomcompose.data.MedicalRecordsRepository
import com.example.hospitalroomcompose.data.local.model.toMedicalRecord
import javax.inject.Inject

class GetMedicalRecordUseCase @Inject constructor(
    private val medicalRecordsRepository: MedicalRecordsRepository
) {
    suspend operator fun invoke (id : Int) = medicalRecordsRepository.getMedicalRecord(id).toMedicalRecord()
}