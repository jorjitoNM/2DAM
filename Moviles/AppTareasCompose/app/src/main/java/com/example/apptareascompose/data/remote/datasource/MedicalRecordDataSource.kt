package com.example.apptareascompose.data.remote.datasource

import com.example.apptareascompose.data.remote.api_services.MedicalRecordService
import javax.inject.Inject

class MedicalRecordDataSource @Inject constructor(
    private val medicalRecordService: MedicalRecordService
) : BaseApiResponse() {

    suspend fun getPatientMedicalRecords(id: Int) = safeApiCall {
        medicalRecordService.getPatientMedicalRecords(id)
    }

    suspend fun getMedicalRecord(id: Int) = safeApiCall {
        medicalRecordService.getMedicalRecord(id)
    }
}