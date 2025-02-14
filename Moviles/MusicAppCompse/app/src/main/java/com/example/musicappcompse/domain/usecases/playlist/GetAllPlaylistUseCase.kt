package com.example.playlistcompose.domain.usecases.playlist

import com.example.musicappcompse.data.PlaylistRepository
import javax.inject.Inject

class GetAllPlaylistUseCase @Inject constructor(
    private val playlistRepository: PlaylistRepository,
) {
    fun invoke() = playlistRepository.getAll()
}