package com.example.myapplication.domain.usecases

import com.example.myapplication.data.SongRepository
import javax.inject.Inject

class GetSongs @Inject constructor(private val songRepository: SongRepository) {
    suspend operator fun invoke() = songRepository.fetchAlbum()
}