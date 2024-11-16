package com.example.myapplication.data.remote.datasource

import com.example.myapplication.R
import com.example.myapplication.data.remote.NetworkResult
import com.example.myapplication.data.remote.apiService.SpotifyService
import com.example.myapplication.data.remote.model.SongRemote
import com.example.myapplication.data.remote.model.getSongsList
import com.example.myapplication.data.remote.model.toSong
import com.example.myapplication.di.IoDispatcher
import com.example.myapplication.domain.model.Song
import com.example.viewmodel.data.remote.datasource.BaseApiResponse
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

class SongsRemoteDataSource @Inject constructor(
    private val spotifyService: SpotifyService,
    @IoDispatcher private val dispatcher: CoroutineDispatcher
) : BaseApiResponse() {
    suspend fun fetchSongs(): NetworkResult<List<Song>> =
        safeApiCall { spotifyService.getAlbum(R.string.album_id.toString()) }.map { album ->
            album?.getSongsList()
        }

    suspend fun fetchSong(id: String): NetworkResult<Song> =
        safeApiCall { spotifyService.getSong(id) }.map { song -> song?.toSong() }
}

