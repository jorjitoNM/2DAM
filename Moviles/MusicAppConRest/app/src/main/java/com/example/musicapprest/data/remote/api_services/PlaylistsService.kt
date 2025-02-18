package com.example.musicapprest.data.remote.api_services

import com.example.musicapprest.domain.model.Playlist
import retrofit2.Response
import retrofit2.http.GET

interface PlaylistsService {
    @GET("playlists/getAll")
    suspend fun getAll () : Response<List<Playlist>>
}