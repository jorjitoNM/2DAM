package com.example.apptareas.data.remote.api_service

import com.example.apptareas.data.remote.model.ApiResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface AppService {
    @GET("posts")
    suspend fun getEvents () : ApiResponse

    @GET("users")
    suspend fun getUsers () : ApiResponse

    @GET("todos/{id}")
    suspend fun getTodos (@Path("id") userId : Int) : ApiResponse

    @GET("comments/{id}")
    suspend fun getComments (@Path("id") id : Int) : ApiResponse

}