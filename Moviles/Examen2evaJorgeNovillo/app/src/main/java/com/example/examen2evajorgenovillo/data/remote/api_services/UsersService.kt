package com.example.examen2evajorgenovillo.data.remote.api_services

import com.example.examen2evajorgenovillo.data.remote.security.Token
import com.example.examen2evajorgenovillo.domain.model.User
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface UsersService {
    @Headers("Accept: application/json, application/*+json")
    @POST("login")
    suspend fun login(
        @Body user: User,
    ): Response<Token>
}