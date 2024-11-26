package com.example.apptareas.data.remote

import com.example.apptareas.data.remote.datasource.UsersDataSource
import com.example.apptareas.domain.model.User
import javax.inject.Inject

class UserRepository @Inject constructor (
    private val usersDataSource: UsersDataSource,
) {
    suspend fun getUsers () : List<User> =
        usersDataSource.getUsers();
}