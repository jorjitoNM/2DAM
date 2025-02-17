package com.example.musicapprest.data.remote.api_services

import com.example.musicapprest.security.Token
import retrofit2.Response
import retrofit2.http.GET

interface UsersService {
    @GET("login")
    suspend fun login (email : String, password : String) : Response<Token>
}