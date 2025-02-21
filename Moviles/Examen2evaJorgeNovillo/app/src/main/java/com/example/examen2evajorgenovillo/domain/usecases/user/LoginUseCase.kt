package com.example.examen2evajorgenovillo.domain.usecases.user

import com.example.examen2evajorgenovillo.data.UsersRepository
import com.example.examen2evajorgenovillo.domain.model.User
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val usersRepository: UsersRepository
) {
    suspend fun invoke(user : User) =
        usersRepository.login(user)
}