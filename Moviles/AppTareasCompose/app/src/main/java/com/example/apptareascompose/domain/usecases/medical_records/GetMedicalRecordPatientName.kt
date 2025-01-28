package com.example.apptareascompose.domain.usecases.medical_records

import com.example.apptareascompose.data.RepositoryRemote
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetMedicalRecordPatientName @Inject constructor(
    private val repositoryRemote: RepositoryRemote
) {
    //fun invoke(patientId : Int) = repositoryRemote.getAllPatients().filter { patient -> patient.map { p -> p.filter { a -> a.id == patientId }.get(0) } } }
}