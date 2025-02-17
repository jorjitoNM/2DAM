package com.example.musicapprest.data.remote.datasource

import com.example.musicapprest.data.remote.api_services.UsersService
import javax.inject.Inject

class UsersDataSource @Inject constructor(
    private val usersService : UsersService,
) : BaseApiResponse() {
}