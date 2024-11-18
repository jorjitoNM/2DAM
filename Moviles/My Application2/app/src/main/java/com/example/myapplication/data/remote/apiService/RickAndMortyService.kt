package com.example.myapplication.data.remote.apiService

import com.example.myapplication.data.remote.model.ApiResponse
import com.example.myapplication.data.remote.model.CharacterRemote
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface RickAndMortyService {
    @GET("character")
    suspend fun getCharacters() : Response<ApiResponse>

    @GET("character/{id}")
    suspend fun getCharacter(@Path("id") id : Int) : Response<CharacterRemote>
}