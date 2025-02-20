package com.example.musicapprest.data.remote.api_services

import com.example.musicapprest.domain.model.Playlist
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface PlaylistsService {
    @GET("playlists/getAll")
    suspend fun getAll () : Response<List<Playlist>>

    @POST("playlists/get")
    suspend fun get (@Body id : Int) : Response<Playlist>

    @PUT("playlists/update")
    suspend fun update (@Body playlist: Playlist) : Response<Playlist>

    @DELETE("playlists/delete/{id}")
    suspend fun delete (@Path("id") id : Int) : Response<Unit>
}