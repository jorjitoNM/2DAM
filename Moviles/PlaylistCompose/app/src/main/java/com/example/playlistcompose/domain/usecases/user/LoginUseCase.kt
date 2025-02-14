package com.example.playlistcompose.domain.usecases.user

import com.example.playlistcompose.data.UsersRepository
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val usersRepository: UsersRepository
) {
    operator fun invoke(username: String, password: String) =
        usersRepository.login(username, password)
}