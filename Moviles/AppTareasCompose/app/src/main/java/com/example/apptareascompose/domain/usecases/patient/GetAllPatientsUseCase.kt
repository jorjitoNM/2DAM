package com.example.apptareascompose.domain.usecases.patient

import com.example.apptareascompose.data.remote.datasource.PatientsDataSource
import javax.inject.Inject

class GetAllPatientsUseCase @Inject constructor(
    private val patientsDataSource: PatientsDataSource
) {
    suspend fun invoke () = patientsDataSource.getAllPatients()
}