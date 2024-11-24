package com.example.apptareas.domain.usecases

import com.example.apptareas.R
import com.example.apptareas.data.remote.UserRepository
import com.example.apptareas.domain.model.User
import com.example.viewmodel.data.remote.NetworkResult
import javax.inject.Inject

class LogInUseCase @Inject constructor(private val userRepository: UserRepository) {
    suspend operator fun invoke(user: User): NetworkResult<User> =
        when (val dbUsers = userRepository.getUsers()) {
            is NetworkResult.Success -> NetworkResult.Success(dbUsers.data.first { u -> u.username == user.username })
            is NetworkResult.Error -> NetworkResult.Error(R.string.login_error.toString())
            is NetworkResult.Loading -> NetworkResult.Loading()
        }
}