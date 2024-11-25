package com.example.myapplication.data

import com.example.myapplication.R
import com.example.myapplication.data.remote.NetworkResult
import com.example.myapplication.data.remote.apiService.RickAndMortyService
import com.example.myapplication.data.remote.model.toCharacter
import com.example.myapplication.domain.model.Character
import timber.log.Timber
import javax.inject.Inject

class SongsRepository @Inject constructor(
    private val rickAndMortyService: RickAndMortyService,
    ) {
    suspend fun fetchCharacters(): NetworkResult<List<Character>> {
        try {
            val response = rickAndMortyService.getCharacters()
            if (response.isSuccessful) {
                return NetworkResult.Success(response.body()!!.characterRemotes.map { c -> c.toCharacter() })
            }
            return error("${response.code()} ${response.message()}")
        } catch (e: Exception) {
            Timber.e(e.message ?: e.toString())
            return error(e.message ?: e.toString())
        }
    }

    suspend fun fetchCharacter (id: Int): NetworkResult<Character?> {
        try {
            val response = rickAndMortyService.getCharacter(id)
            if (response.isSuccessful) {
                return NetworkResult.Success(response.body()?.toCharacter())
            }
            return error("${response.code()} ${response.message()}")
        } catch (e: Exception) {
            Timber.e(e.message ?: e.toString())
            return error(e.message ?: e.toString())
        }
    }

    private fun <T> error(errorMessage: String): NetworkResult<T> =
        NetworkResult.Error("${R.string.callFailed} $errorMessage")
}