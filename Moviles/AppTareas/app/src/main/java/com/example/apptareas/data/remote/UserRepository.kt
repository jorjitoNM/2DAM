package com.example.apptareas.data.remote

import com.example.apptareas.data.remote.datasource.UsersDataSource
import com.example.apptareas.domain.model.User
import javax.inject.Inject

class UserRepository @Inject constructor (
    private val usersDataSource: UsersDataSource,
) {
    suspend fun getUsers () : NetworkResult<List<User>> =
        usersDataSource.getUsers();

    suspend fun getUser(userId: Int) : NetworkResult<User> =
        usersDataSource.getUser(userId)
}