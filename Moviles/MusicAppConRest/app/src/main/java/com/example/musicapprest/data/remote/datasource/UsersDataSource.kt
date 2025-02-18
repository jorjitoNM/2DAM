package com.example.musicapprest.data.remote.datasource

import com.example.musicapprest.common.NetworkResult
import com.example.musicapprest.data.remote.api_services.UsersService
import com.example.musicapprest.data.remote.security.Token
import com.example.musicapprest.domain.model.User
import javax.inject.Inject

class UsersDataSource @Inject constructor(
    private val usersService : UsersService,
) : BaseApiResponse() {

    suspend fun login (user : User) : NetworkResult<Token> = safeApiCall {
        usersService.login(user)
    }

    suspend fun register (user  : User) : NetworkResult<String> = safeApiCall {
        usersService.register(user)
    }
}