package com.example.playlistcompose.domain.usecases.user

import com.example.playlistcompose.data.UsersRepository
import javax.inject.Inject

class RegisterUserUseCase @Inject constructor(
    private val usersRepository: UsersRepository
) {
    suspend operator fun invoke(username: String, password: String) =
        usersRepository.registerUser(username, password)
}