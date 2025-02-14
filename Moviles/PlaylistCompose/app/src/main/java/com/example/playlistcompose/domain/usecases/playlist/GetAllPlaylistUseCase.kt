package com.example.playlistcompose.domain.usecases.playlist

import com.example.playlistcompose.data.PlaylistRepository
import javax.inject.Inject

class GetAllPlaylistUseCase @Inject constructor(
    private val playlistRepository: PlaylistRepository,
) {
    fun invoke() = playlistRepository.getAll()
}