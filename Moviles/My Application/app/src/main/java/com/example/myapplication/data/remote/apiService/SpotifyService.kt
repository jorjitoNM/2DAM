package com.example.myapplication.data.remote.apiService

import com.example.myapplication.data.remote.model.Album
import com.example.myapplication.data.remote.model.SongRemote
import com.example.myapplication.data.remote.model.Token
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path

interface SpotifyService {
    @GET("/v1/albums/{id}")
    suspend fun getAlbum(@Path("id") id: String, @Header("Authorization") authorization : String) : Response<Album>

    @GET("/v1/tracks/{id}")
    suspend fun getSong(@Path("id") id : String, @Header("Authorization") authorization : String) : Response<SongRemote>

    @POST("/api/token")
    suspend fun getToken(@Header("Content-type") content : String, @Body grant_type : String) : Response<Token>
}