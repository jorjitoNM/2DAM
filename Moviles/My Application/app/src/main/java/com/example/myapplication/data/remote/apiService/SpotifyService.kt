package com.example.myapplication.data.remote.apiService

import com.example.myapplication.data.remote.model.Album
import com.example.myapplication.data.remote.model.SongRemote
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface SpotifyService {
    @GET("/v1/albums/{id}")
    suspend fun getAlbum(@Path("id") id: String) : Response<Album?>

    @GET("/v1/tracks/{id}")
    suspend fun getSong(@Path("id") id : String) : Response<SongRemote>

}