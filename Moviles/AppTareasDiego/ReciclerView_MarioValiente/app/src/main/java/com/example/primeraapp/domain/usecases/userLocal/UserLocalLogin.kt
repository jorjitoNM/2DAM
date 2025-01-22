package com.example.primeraapp.domain.usecases.userLocal

import com.example.primeraapp.data.RepositoryLocal
import javax.inject.Inject


class UserLocalLogin @Inject constructor(private val repositorio: RepositoryLocal) {
    operator fun invoke(username: String, password: String) =
        repositorio.validateUser(username, password)
}
