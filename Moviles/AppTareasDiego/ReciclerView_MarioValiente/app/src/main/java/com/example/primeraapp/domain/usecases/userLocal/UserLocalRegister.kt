package com.example.primeraapp.domain.usecases.userLocal

import com.example.primeraapp.data.RepositoryLocal
import javax.inject.Inject


class UserLocalRegister @Inject constructor(private val repositorio: RepositoryLocal) {
    suspend operator fun invoke(username: String, password: String) =
        repositorio.registerUser(username, password)
}
