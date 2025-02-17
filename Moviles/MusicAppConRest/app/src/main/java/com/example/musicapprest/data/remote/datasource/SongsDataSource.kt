package com.example.musicapprest.data.remote.datasource

import com.example.musicapprest.data.remote.api_services.SongsService
import com.example.musicapprest.domain.model.Song
import javax.inject.Inject

class SongsDataSource @Inject constructor(
    private val songsService: SongsService,
) : BaseApiResponse() {

    suspend fun getAll () : List<Song> {return emptyList()}

}