package com.example.musicapprest.domain.usecases.song

import com.example.musicapprest.data.SongsRepository
import javax.inject.Inject

class GetAllSongsUseCase @Inject constructor(
    private val songsRepository: SongsRepository
) {
    suspend fun invoke () = songsRepository.getAll()
}