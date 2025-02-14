package com.example.musiccompose.domain.usecases.user

import com.example.musiccompose.data.UsersRepository
import javax.inject.Inject

class RegisterUserUseCase @Inject constructor(
    private val usersRepository: UsersRepository
) {
    suspend fun invoke(username: String, password: String) =
        usersRepository.registerUser(username, password)
}