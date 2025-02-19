package com.example.musicapprest.data.remote.api_services

import com.example.musicapprest.data.remote.security.Token
import com.example.musicapprest.domain.model.User
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.POST

interface UsersService {
    @Headers("Accept: application/json, application/*+json")
    @POST("login")
    suspend fun login(
        @Body user: User,
    ): Response<Token>

    @POST("refresh")
    suspend fun refreshToken(
        @Header("Authorization") token: String,
    ): Response<Token>

    @POST("signUp")
    suspend fun register (
        @Body user : User
    ) : Response<String>
}