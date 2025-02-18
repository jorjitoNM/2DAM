package com.example.musicapprest.domain.usecases.user

import com.example.musicapprest.data.UsersRepository
import com.example.musicapprest.domain.model.User
import javax.inject.Inject

class RegisterUserUseCase @Inject constructor(
    private val usersRepository: UsersRepository
) {
    suspend fun invoke(user : User) = usersRepository.registerUser(user)
}