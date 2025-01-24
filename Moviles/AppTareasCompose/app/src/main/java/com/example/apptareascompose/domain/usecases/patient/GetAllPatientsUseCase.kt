package com.example.apptareascompose.domain.usecases.patient

import com.example.apptareascompose.data.RepositoryRemote
import javax.inject.Inject

class GetAllPatientsUseCase @Inject constructor(
    private val repositoryRemote: RepositoryRemote
) {
    suspend fun invoke () = repositoryRemote.getAllPatients()
}