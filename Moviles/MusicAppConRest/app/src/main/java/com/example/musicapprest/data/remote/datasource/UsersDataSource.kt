package com.example.musicapprest.data.remote.datasource

import com.example.musicapprest.data.remote.api_services.UsersService
import com.example.musicapprest.domain.model.User
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class UsersDataSource @Inject constructor(
    private val usersService : UsersService,
) : BaseApiResponse() {

    suspend fun login (username: String, password: String) : Flow<Result<User?>> {
        return flow {  }
    }

    suspend fun register (username: String, password: String) : Result<Unit> {
        return Result.failure(Exception())
    }
}