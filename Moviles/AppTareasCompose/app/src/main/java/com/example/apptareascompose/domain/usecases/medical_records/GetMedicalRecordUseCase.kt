package com.example.apptareascompose.domain.usecases.medical_records

import com.example.apptareascompose.data.RepositoryRemote
import javax.inject.Inject

class GetMedicalRecordUseCase @Inject constructor(
    private val repositoryRemote: RepositoryRemote
) {
    fun invoke (id : Int) = repositoryRemote.getMedicalRecord(id)
}