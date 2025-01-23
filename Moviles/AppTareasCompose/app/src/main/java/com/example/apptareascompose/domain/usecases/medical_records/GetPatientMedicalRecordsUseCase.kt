package com.example.apptareascompose.domain.usecases.medical_records

import com.example.apptareascompose.data.remote.datasource.MedicalRecordDataSource
import javax.inject.Inject

class GetPatientMedicalRecordsUseCase @Inject constructor(
    private val medicalRecordDataSource: MedicalRecordDataSource
) {
    suspend fun invoke (id : Int) = medicalRecordDataSource.getPatientMedicalRecords(id)
}