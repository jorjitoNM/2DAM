package com.example.apptareas.domain.usecases

import com.example.apptareas.R
import com.example.apptareas.data.remote.UserRepository
import com.example.apptareas.domain.model.User
import com.example.apptareas.data.remote.NetworkResult
import com.example.apptareas.domain.model.validateUser
import com.example.apptareas.utilities.Constantes
import javax.inject.Inject

class LogInUseCase @Inject constructor(private val userRepository: UserRepository) {
    suspend operator fun invoke(user: User): NetworkResult<List<User>> =
        user.validateUser(user).then {_ ->
            when (val result = userRepository.getUsers()) {
                is NetworkResult.Success -> return NetworkResult.Success(result.data)
                    is NetworkResult.Error -> NetworkResult.Error(Constantes.LOGIN_ERROR)
                is NetworkResult.Loading -> NetworkResult.Loading()
            }
        }
}