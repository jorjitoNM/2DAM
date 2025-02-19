package com.example.musicapprest.domain.usecases.song

import com.example.musicapprest.common.NetworkResult
import com.example.musicapprest.data.SongsRepository
import com.example.musicapprest.domain.model.Song
import javax.inject.Inject

class GetAllSongsUseCase @Inject constructor(
    private val songsRepository: SongsRepository
) {
    suspend fun invoke () : NetworkResult<List<Song>> = songsRepository.getAll()
}