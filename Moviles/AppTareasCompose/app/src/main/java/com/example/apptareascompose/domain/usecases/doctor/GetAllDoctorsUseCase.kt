package com.example.apptareascompose.domain.usecases.doctor

import com.example.apptareascompose.data.RepositoryRemote
import javax.inject.Inject

class GetAllDoctorsUseCase @Inject constructor(
    private val repositoryRemote: RepositoryRemote
){
    fun invoke () = repositoryRemote.getAllDoctors()
}