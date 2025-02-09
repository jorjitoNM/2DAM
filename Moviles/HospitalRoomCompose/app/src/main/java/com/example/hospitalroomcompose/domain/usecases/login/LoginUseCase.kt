package com.example.hospitalroomcompose.domain.usecases.login

import com.example.hospitalroomcompose.data.RepositoryLocal
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val repositoryLocal: RepositoryLocal
) {
    operator fun invoke(username: String, password: String) =
        repositoryLocal.login(username, password)
}