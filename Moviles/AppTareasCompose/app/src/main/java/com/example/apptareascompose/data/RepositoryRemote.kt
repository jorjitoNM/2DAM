package com.example.apptareascompose.data

import com.example.apptareascompose.data.remote.datasource.MedicalRecordDataSource
import com.example.apptareascompose.data.remote.datasource.PatientsDataSource
import com.example.primeraapp.di.IoDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

class RepositoryRemote @Inject constructor(
    private val patientsDataSource: PatientsDataSource,
    private val medicalRecordDataSource : MedicalRecordDataSource,
    @IoDispatcher val ioDispatcher: CoroutineDispatcher
) {

}
