package com.example.myapplication.data

import com.example.myapplication.R
import com.example.myapplication.data.remote.NetworkResult
import com.example.myapplication.data.remote.apiService.SpotifyService
import com.example.myapplication.data.remote.datasource.SongsRemoteDataSource
import com.example.myapplication.data.remote.model.getKey
import com.example.myapplication.data.remote.model.getSongsList
import com.example.myapplication.data.remote.model.toSong
import com.example.myapplication.domain.model.Song
import javax.inject.Inject
import kotlin.io.encoding.Base64
import kotlin.io.encoding.ExperimentalEncodingApi

class SongsRepository @Inject constructor(
    private val spotifyService: SpotifyService,
    private val songsRemoteDataSource: SongsRemoteDataSource,
    ) {


    suspend fun fetchAlbum2(authorization : String): NetworkResult<List<Song>?> {
        return songsRemoteDataSource.fetchAlbum(authorization)
    }

    suspend fun fetchSong2 (id: String, authorization : String): NetworkResult<Song?> {
        return songsRemoteDataSource.fetchSong(id,authorization)
    }

    @OptIn(ExperimentalEncodingApi::class)
    suspend fun fetchToken2 (): NetworkResult<String> {
        val clientCredentials = "${R.string.clientID}${R.string.clientSecret}"
        val base64ClientCredential  = Base64.encode(clientCredentials.toByteArray())
        return songsRemoteDataSource.fetchToken(R.string.tokenRequestContentType.toString(),base64ClientCredential)
    }
    suspend fun fetchAlbum(authorization : String): NetworkResult<List<Song>?> {
        try {
            val response = spotifyService.getAlbum(R.string.album_id.toString(),authorization)
            if (response.isSuccessful) {
                val body = response.body()
                body?.let {
                    return NetworkResult.Success(body.getSongsList())
                }
            }
            return error("${response.code()} ${response.message()}")
        } catch (e: Exception) {
            return error(e.message ?: e.toString())
        }
    }

    suspend fun fetchSong (id: String, authorization : String): NetworkResult<Song?> {
        try {
            val response = spotifyService.getSong(id,authorization)
            if (response.isSuccessful) {
                val body = response.body()
                body?.let {
                    return NetworkResult.Success(body.toSong())
                }
            }
            return error("${response.code()} ${response.message()}")
        } catch (e: Exception) {
            return error(e.message ?: e.toString())
        }
    }

    @OptIn(ExperimentalEncodingApi::class)
    suspend fun fetchToken (): NetworkResult<String> {
        val clientCredentials = "${R.string.clientID}${R.string.clientSecret}"
        val base64ClientCredential  = Base64.encode(clientCredentials.toByteArray())
        try {
            val response = spotifyService.getToken(R.string.tokenRequestContentType.toString(),base64ClientCredential)
            if (response.isSuccessful) {
                val body = response.body()
                body?.let {
                    return NetworkResult.Success(body.getKey())
                }
            }
            return error("${response.code()} ${response.message()}")
        } catch (e: Exception) {
            return error(e.message ?: e.toString())
        }
    }



    private fun <T> error(errorMessage: String): NetworkResult<T> =
        NetworkResult.Error("Api call failed $errorMessage")

}