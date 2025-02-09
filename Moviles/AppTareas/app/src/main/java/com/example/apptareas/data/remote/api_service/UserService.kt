package com.example.apptareas.data.remote.api_service

import com.example.apptareas.data.remote.model.user.UserRemote
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface UserService {

    @GET("users")
    suspend fun getUsers () : Response<List<UserRemote>>

    @GET("users/{id}")
    suspend fun getUser (@Path("id") userId : Int) : Response<UserRemote>
}