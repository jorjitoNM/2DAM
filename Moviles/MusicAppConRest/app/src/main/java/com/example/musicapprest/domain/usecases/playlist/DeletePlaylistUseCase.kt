package com.example.musicapprest.domain.usecases.playlist

import com.example.musicapprest.common.NetworkResult
import com.example.musicapprest.data.PlaylistRepository
import javax.inject.Inject

class DeletePlaylistUseCase @Inject constructor(
    private val playlistRepository: PlaylistRepository
) {
    suspend fun invoke (id : Int) : NetworkResult<Unit> = playlistRepository.delete(id)
}