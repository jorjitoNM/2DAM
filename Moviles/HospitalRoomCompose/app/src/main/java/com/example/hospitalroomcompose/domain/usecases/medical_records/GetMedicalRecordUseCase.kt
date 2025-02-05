package com.example.apptareascompose.domain.usecases.medical_records

import com.example.hospitalroomcompose.data.RepositoryLocal
import javax.inject.Inject

class GetMedicalRecordUseCase @Inject constructor(
    private val repositoryLocal: RepositoryLocal
) {
    fun invoke (id : Int) = {  }//repositoryLocal.getMedicalRecord(id)
}