package com.example.musicapprest.data.remote.api_services

import com.example.musicapprest.domain.model.Playlist
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface PlaylistsService {
    @GET("playlists/getAll")
    suspend fun getAll () : Response<List<Playlist>>

    @POST("playlist/get")
    suspend fun get (@Body id : Int) : Response<Playlist>

    @POST("playlist/update")
    suspend fun update (@Body playlist: Playlist) : Response<Playlist>
}