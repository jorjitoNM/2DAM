package com.example.apptareas.data.remote

import com.example.apptareas.data.remote.datasource.UsersDataSource
import com.example.apptareas.domain.model.User
import com.example.viewmodel.data.remote.NetworkResult

class UserRepository (
    private val usersDataSource: UsersDataSource,
) {
    suspend fun getUsers () : NetworkResult<List<User>> =
        usersDataSource.getUsers();
}