package com.example.apptareascompose.data.remote.datasource

import com.example.apptareascompose.data.remote.api_services.PatientService
import javax.inject.Inject


class PatientsDataSource @Inject constructor(
    private val patientService: PatientService,
) : BaseApiResponse() {

    suspend fun getAllPatients () = safeApiCall {
        patientService.getAllPatients()
    }
}