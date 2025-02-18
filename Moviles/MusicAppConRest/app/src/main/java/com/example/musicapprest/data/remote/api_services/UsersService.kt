package com.example.musicapprest.data.remote.api_services

import com.example.musicapprest.data.remote.security.Token
import com.example.musicapprest.domain.model.User
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface UsersService {
    @POST("login")
    suspend fun login(
        @Body user: User,
    ): Response<Token>

    @GET("refresh")
    suspend fun refreshToken(
        @Header("Authorization") token: String,
    ): Response<Token>

    @POST("signUp")
    suspend fun register (
        @Body user : User
    ) : Response<String>
}