package com.example.primeraapp.domain.usecases.userRemote

import com.example.primeraapp.data.RepositoryRemote
import javax.inject.Inject

class GetUserUseCase @Inject constructor(private val repositorio: RepositoryRemote) {
    operator fun invoke(userId: Int) = repositorio.fetchUser(userId)
}