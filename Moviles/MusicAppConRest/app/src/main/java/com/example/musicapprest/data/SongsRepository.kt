package com.example.musicapprest.data

import com.example.musicapprest.data.remote.datasource.SongsDataSource
import javax.inject.Inject

class SongsRepository @Inject constructor(
    private val songsDataSource: SongsDataSource,
) {
    suspend fun getAll() = songsDataSource.getAll()
}