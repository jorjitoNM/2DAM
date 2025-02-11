package com.example.myapplication.data.remote.datasource

import com.example.myapplication.R
import com.example.myapplication.data.remote.NetworkResult
import com.example.myapplication.data.remote.apiService.SpotifyService
import com.example.myapplication.data.remote.model.getKey
import com.example.myapplication.data.remote.model.getSongsList
import com.example.myapplication.data.remote.model.toSong
import com.example.myapplication.di.IoDispatcher
import com.example.myapplication.domain.model.Song
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

class SongsRemoteDataSource @Inject constructor(
    private val spotifyService: SpotifyService,
    @IoDispatcher private val dispatcher: CoroutineDispatcher
) : BaseApiResponse() {
    suspend fun fetchAlbum(authorization : String): NetworkResult<List<Song>?> =
        safeApiCall { spotifyService.getAlbum(R.string.album_id.toString(),authorization) }.map { album ->
            album?.getSongsList()
        }

    suspend fun fetchSong(id: String,authorization : String): NetworkResult<Song?> =
        safeApiCall { spotifyService.getSong(id,authorization) }.map { song -> song.toSong() }

    suspend fun fetchToken (contentType: String, grant_type : String) : NetworkResult<String> =
        safeApiCall { spotifyService.getToken(contentType,grant_type) }.map { token -> token.getKey() }
}

