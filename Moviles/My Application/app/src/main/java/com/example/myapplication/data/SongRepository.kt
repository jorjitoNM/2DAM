package com.example.myapplication.data

import com.example.myapplication.data.remote.NetworkResult
import com.example.myapplication.data.remote.apiService.SpotifyService
import com.example.myapplication.data.remote.model.Album
import com.example.myapplication.data.remote.model.toSong
import com.example.myapplication.domain.model.Song
import com.example.myapplication.data.remote.datasource.SongsRemoteDataSource

import javax.inject.Inject

class SongRepository @Inject constructor(
    private val spotifyService: SpotifyService,
    private val songsRemoteDataSource: SongsRemoteDataSource,
    ) {



    suspend fun fetchAlbum(): NetworkResult<List<Song>?> {
        return songsRemoteDataSource.fetchSongs()
    }

    suspend fun fetchSong (id: String): NetworkResult<Song> {

        try {
            val response = spotifyService.getSong(id)
            if (response.isSuccessful) {
                val body = response.body()
                body?.let {
                    return NetworkResult.Success(body.toSong())
                }
            }
            return NetworkResult.Error("${response.code()} ${response.message()}")
        } catch (e: Exception) {
            return NetworkResult.Error(e.message ?: e.toString())
        }

    }



    private fun <T> error(errorMessage: String): NetworkResult<T> =
        NetworkResult.Error("Api call failed $errorMessage")

}