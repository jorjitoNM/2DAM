package com.example.examen2evajorgenovillo.data.remote.datasource

import com.example.examen2evajorgenovillo.common.NetworkResult
import com.example.examen2evajorgenovillo.data.remote.api_services.UsersService
import com.example.examen2evajorgenovillo.data.remote.security.Token
import com.example.examen2evajorgenovillo.domain.model.User
import javax.inject.Inject

class UsersDataSource @Inject constructor(
    private val usersService : UsersService,
) : BaseApiResponse() {

    suspend fun login (user : User) : NetworkResult<Token> = safeApiCall {
        usersService.login(user)
    }
}