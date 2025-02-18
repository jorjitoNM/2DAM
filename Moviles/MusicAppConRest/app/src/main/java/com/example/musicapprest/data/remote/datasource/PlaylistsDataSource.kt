package com.example.musicapprest.data.remote.datasource

import com.example.musicapprest.data.remote.api_services.PlaylistsService
import com.example.musicapprest.domain.model.Playlist
import javax.inject.Inject

class PlaylistsDataSource @Inject constructor(
    private val playlistsService: PlaylistsService
) : BaseApiResponse() {

    suspend fun getAll () = safeApiCall {
        playlistsService.getAll()
    }

    suspend fun get (playlistId : Int) : Playlist {return Playlist()}

    suspend fun update (playlist: Playlist) {}
}