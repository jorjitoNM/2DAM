package com.example.musiccompose.domain.usecases.playlist

import com.example.musiccompose.data.PlaylistRepository
import javax.inject.Inject

class GetAllPlaylistUseCase @Inject constructor(
    private val playlistRepository: PlaylistRepository,
) {
    fun invoke() = playlistRepository.getAll()
}