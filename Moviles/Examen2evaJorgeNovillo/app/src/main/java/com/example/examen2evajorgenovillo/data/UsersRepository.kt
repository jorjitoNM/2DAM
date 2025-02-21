package com.example.examen2evajorgenovillo.data

import com.example.examen2evajorgenovillo.common.NetworkResult
import com.example.examen2evajorgenovillo.data.remote.datasource.UsersDataSource
import com.example.examen2evajorgenovillo.data.remote.security.Token
import com.example.examen2evajorgenovillo.domain.model.User
import javax.inject.Inject

class UsersRepository @Inject constructor(
    private val usersDataSource: UsersDataSource,
) {

    suspend fun login(user : User) : NetworkResult<Token> = usersDataSource.login(user)
}