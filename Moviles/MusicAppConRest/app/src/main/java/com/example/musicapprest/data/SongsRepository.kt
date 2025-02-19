package com.example.musicapprest.data

import com.example.musicapprest.common.NetworkResult
import com.example.musicapprest.data.remote.datasource.SongsDataSource
import com.example.musicapprest.domain.model.Song
import javax.inject.Inject

class SongsRepository @Inject constructor(
    private val songsDataSource: SongsDataSource,
) {
    suspend fun getAll() : NetworkResult<List<Song>> = songsDataSource.getAll()
}