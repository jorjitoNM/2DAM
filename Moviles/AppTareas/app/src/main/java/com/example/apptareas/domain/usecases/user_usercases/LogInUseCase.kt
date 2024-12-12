package com.example.apptareas.domain.usecases.user_usercases

import com.example.apptareas.data.remote.NetworkResult
import com.example.apptareas.data.remote.UserRepository
import com.example.apptareas.domain.model.User
import com.example.apptareas.domain.model.validateUser
import javax.inject.Inject

class LogInUseCase @Inject constructor(private val userRepository: UserRepository) {
    suspend operator fun invoke(user: User): NetworkResult<List<User>> =
        user.validateUser(user).then {_ -> userRepository.getUsers() }
}