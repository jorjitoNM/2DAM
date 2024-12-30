package com.example.apptareas.domain.usecases.user_usercases

import com.example.apptareas.data.NetworkResult
import com.example.apptareas.data.UserRepository
import com.example.apptareas.domain.model.User
import com.example.apptareas.domain.model.validateUser
import javax.inject.Inject

class LogInUseCase @Inject constructor(private val userRepository: UserRepository) {
    operator fun invoke(user: User) : NetworkResult<Boolean> {
        user.validateUser(user).then { _ ->
            return true
        }
    }
}