package com.example.primeraapp.domain.usecases.userRemote

import com.example.primeraapp.data.RepositoryRemote
import com.example.primeraapp.domain.modelo.User
import javax.inject.Inject

class UpdateUserUseCase @Inject constructor(private val repositorio: RepositoryRemote) {
    operator fun invoke(id: Int, user: User) = repositorio.updateUser(id, user)

}