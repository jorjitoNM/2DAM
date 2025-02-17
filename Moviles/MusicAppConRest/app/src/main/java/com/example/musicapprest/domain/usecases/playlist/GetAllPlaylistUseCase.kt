package com.example.musicapprest.domain.usecases.playlist

import com.example.musicapprest.data.PlaylistRepository
import javax.inject.Inject

class GetAllPlaylistUseCase @Inject constructor(
    private val playlistRepository: PlaylistRepository,
) {
    suspend fun invoke() = playlistRepository.getAll()
}