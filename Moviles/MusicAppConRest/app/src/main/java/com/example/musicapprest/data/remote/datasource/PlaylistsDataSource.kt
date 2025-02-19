package com.example.musicapprest.data.remote.datasource

import com.example.musicapprest.common.NetworkResult
import com.example.musicapprest.data.remote.api_services.PlaylistsService
import com.example.musicapprest.domain.model.Playlist
import javax.inject.Inject

class PlaylistsDataSource @Inject constructor(
    private val playlistsService: PlaylistsService
) : BaseApiResponse() {

    suspend fun getAll () : NetworkResult<List<Playlist>> = safeApiCall {
        playlistsService.getAll()
    }

    suspend fun get (playlistId : Int) : NetworkResult<Playlist> = safeApiCall {
        playlistsService.get(playlistId)
    }

    suspend fun update (playlist: Playlist) : NetworkResult<Playlist> = safeApiCall {
        playlistsService.update(playlist)
    }

    suspend fun delete (id : Int) : NetworkResult<Unit> = safeApiCall {
        playlistsService.delete(id)
    }
}