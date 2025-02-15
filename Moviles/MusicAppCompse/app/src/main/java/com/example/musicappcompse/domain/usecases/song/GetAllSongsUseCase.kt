package com.example.musicappcompse.domain.usecases.song

import com.example.musicappcompse.data.SongsRepository
import javax.inject.Inject

class GetAllSongsUseCase @Inject constructor(
    private val songsRepository: SongsRepository
) {
    fun invoke () = songsRepository.getAll()
}