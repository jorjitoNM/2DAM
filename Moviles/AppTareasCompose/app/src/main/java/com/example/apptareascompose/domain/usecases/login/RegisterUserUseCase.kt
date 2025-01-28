package com.example.apptareascompose.domain.usecases.login

import com.example.apptareascompose.data.RepositoryLocal
import javax.inject.Inject

class RegisterUserUseCase @Inject constructor(
    private val repositoryLocal: RepositoryLocal
) {
    suspend operator fun invoke(username: String, password: String) =
        repositoryLocal.registerUser(username, password)
}