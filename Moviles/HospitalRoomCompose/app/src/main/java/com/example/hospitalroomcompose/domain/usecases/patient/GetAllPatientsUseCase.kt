package com.example.hospitalroomcompose.domain.usecases.patient

import com.example.hospitalroomcompose.data.RepositoryLocal
import com.example.hospitalroomcompose.data.local.model.toPatient
import javax.inject.Inject

class GetAllPatientsUseCase @Inject constructor(
    private val repositoryLocal: RepositoryLocal
) {
    suspend fun invoke () = repositoryLocal.getAllPatients().map { p -> p.toPatient() }
}