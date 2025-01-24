package com.example.apptareascompose.domain.usecases.login

import com.example.apptareascompose.common.NetworkResult
import com.example.apptareascompose.data.RepositoryLocal
import com.example.apptareascompose.domain.model.User
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val repositoryLocal: RepositoryLocal
) {
    fun invoke (user : User) : Flow<NetworkResult<User>> = TODO()//repositoryLocal.getUser(user)
}