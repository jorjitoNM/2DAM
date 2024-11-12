package com.example.myapplication.data.remote.apiService

import retrofit2.Response
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface SpotifyService {
    @GET("/users")
    suspend fun getUsers(@Query("user") user: String) : Response<List<UserRemote>>


    @GET("/users/{id}")
    suspend fun getUser(@Path("id") id : Int) : Response<UserRemote>


    @DELETE("/users/{id}")
    suspend fun delUser(@Path("id") id : Int) : Response<Unit>
}