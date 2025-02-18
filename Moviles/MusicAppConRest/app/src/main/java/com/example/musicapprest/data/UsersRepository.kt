package com.example.musicapprest.data

import com.example.musicapprest.data.remote.datasource.UsersDataSource
import com.example.musicapprest.domain.model.User
import javax.inject.Inject

class UsersRepository @Inject constructor(
    private val usersDataSource: UsersDataSource,
) {
    suspend fun registerUser(user : User) =
        usersDataSource.register(user)

    suspend fun login(user : User) = usersDataSource.login(user)
}