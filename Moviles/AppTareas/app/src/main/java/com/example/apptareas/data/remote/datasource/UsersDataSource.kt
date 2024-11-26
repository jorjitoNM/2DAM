package com.example.apptareas.data.remote.datasource

import com.example.apptareas.data.remote.NetworkResult
import com.example.apptareas.data.remote.api_service.UserService
import com.example.apptareas.data.remote.model.user.toUser
import com.example.apptareas.domain.model.User
import javax.inject.Inject

class UsersDataSource @Inject constructor (
    private val userService: UserService,
) : BaseApiResponse() {

    suspend fun getUsers () : List<User> =
        safeApiCall { userService.getUsers() }.map { response -> response.map { u -> u.toUser() } }
}