package com.example.apptareas.data.remote.api_service

import com.example.apptareas.data.remote.model.user.UserRemote
import retrofit2.Response
import retrofit2.http.GET

interface UserService {

    @GET("users")
    suspend fun getUsers () : Response<List<UserRemote>>
}