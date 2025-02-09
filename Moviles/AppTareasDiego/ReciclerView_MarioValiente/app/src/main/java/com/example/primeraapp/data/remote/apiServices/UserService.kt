package com.example.primeraapp.data.remote.apiServices

import com.example.primeraapp.data.remote.modelo.UserRemote
import com.example.primeraapp.domain.modelo.User
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PUT
import retrofit2.http.Path

interface UserService {
    @PUT("users/{id}")
    suspend fun putUser(@Path("id") id: Int, @Body user: User): Response<Unit>

    @GET("users/{id}")
    suspend fun getUser(@Path("id") id: Int): Response<UserRemote>
}