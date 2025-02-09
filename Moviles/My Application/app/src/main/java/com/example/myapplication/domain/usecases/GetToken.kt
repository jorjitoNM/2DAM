package com.example.myapplication.domain.usecases

import com.example.myapplication.data.SongsRepository
import javax.inject.Inject

class GetToken @Inject constructor(private val songsRepository: SongsRepository) {
    suspend operator fun invoke() = songsRepository.fetchToken()
}