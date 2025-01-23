package com.example.apptareascompose.data.remote.api_services

import com.example.apptareascompose.domain.model.MedicalRecord
import retrofit2.Response
import retrofit2.http.GET

interface MedicalRecordService {

    @GET("patients/{id}/medRecords")
    suspend fun getPatientMedicalRecords (id : Int) : Response<List<MedicalRecord>>

    @GET("patients/medRecords/id")
    suspend fun getMedicalRecord (id : Int) : Response<MedicalRecord>
}