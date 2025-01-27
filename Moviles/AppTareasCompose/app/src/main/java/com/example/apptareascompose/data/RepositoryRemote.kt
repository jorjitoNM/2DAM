package com.example.apptareascompose.data

import com.example.apptareascompose.common.NetworkResult
import com.example.apptareascompose.data.remote.datasource.MedicalRecordDataSource
import com.example.apptareascompose.data.remote.datasource.PatientsDataSource
import com.example.apptareascompose.data.utils.Constantes
import com.example.primeraapp.di.IoDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class RepositoryRemote @Inject constructor(
    private val patientsDataSource: PatientsDataSource,
    private val medicalRecordDataSource : MedicalRecordDataSource,
    @IoDispatcher val dispatcher: CoroutineDispatcher,
) {
    fun getAllPatients () = flow {
        emit(NetworkResult.Loading())
        val result = patientsDataSource.getAllPatients()
        emit(result)
    }
        .catch { e ->
        emit(NetworkResult.Error(e.message ?: Constantes.DATA_BASE_ERROR))
    }
        .flowOn(dispatcher)

    fun getPatientMedicalRecords(id: Int) = flow {
        emit(NetworkResult.Loading())
        val result = medicalRecordDataSource.getPatientMedicalRecords(id)
        emit(result)
    }
        .catch { e ->
            emit(NetworkResult.Error(e.message ?: Constantes.DATA_BASE_ERROR))
        }
        .flowOn(dispatcher)
}
