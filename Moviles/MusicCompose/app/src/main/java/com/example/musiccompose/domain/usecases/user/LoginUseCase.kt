package com.example.musiccompose.domain.usecases.user

import com.example.musiccompose.data.UsersRepository
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val usersRepository: UsersRepository
) {
    suspend fun invoke(username: String, password: String) =
        usersRepository.login(username, password)
}