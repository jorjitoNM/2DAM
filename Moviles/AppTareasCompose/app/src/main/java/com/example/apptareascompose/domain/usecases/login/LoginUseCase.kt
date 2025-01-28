package com.example.apptareascompose.domain.usecases.login

import com.example.apptareascompose.data.RepositoryLocal
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val repositoryLocal: RepositoryLocal
) {
    operator fun invoke(username: String, password: String) =
        repositoryLocal.validateUser(username, password)
}