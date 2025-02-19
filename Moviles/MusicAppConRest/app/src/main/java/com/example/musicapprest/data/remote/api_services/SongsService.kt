package com.example.musicapprest.data.remote.api_services

import com.example.musicapprest.domain.model.Song
import retrofit2.Response
import retrofit2.http.GET

interface SongsService {

    @GET("songs/getAll")
    suspend fun getAll () : Response<List<Song>>
}