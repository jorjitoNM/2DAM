package com.example.apptareas.domain.usecases.user_usercases

import com.example.apptareas.data.remote.UserRepository
import javax.inject.Inject

class GetUserUseCase @Inject constructor(private val userRepository: UserRepository) {
    suspend operator fun invoke (userId : Int) =
        userRepository.getUser(userId)
}