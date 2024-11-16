package com.example.myapplication.data

import com.example.myapplication.data.remote.NetworkResult
import com.example.myapplication.data.remote.datasource.SongsRemoteDataSource
import com.example.myapplication.domain.model.Song
import javax.inject.Inject

class SongRepository @Inject constructor(
    private val songsRemoteDataSource: SongsRemoteDataSource,
    ) {


    suspend fun fetchAlbum(): NetworkResult<List<Song>> {
        return songsRemoteDataSource.fetchSongs()
    }

    suspend fun fetchSong (id: String): NetworkResult<Song> {
        return songsRemoteDataSource.fetchSong(id)
    }


    private fun <T> error(errorMessage: String): NetworkResult<T> =
        NetworkResult.Error("Api call failed $errorMessage")

}