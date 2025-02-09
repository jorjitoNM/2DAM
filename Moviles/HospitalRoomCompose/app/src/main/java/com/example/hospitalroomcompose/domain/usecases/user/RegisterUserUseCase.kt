package com.example.hospitalroomcompose.domain.usecases.user

import com.example.hospitalroomcompose.data.UsersRepository
import javax.inject.Inject

class RegisterUserUseCase @Inject constructor(
    private val usersRepository: UsersRepository
) {
    suspend operator fun invoke(username: String, password: String) =
        usersRepository.registerUser(username, password)
}