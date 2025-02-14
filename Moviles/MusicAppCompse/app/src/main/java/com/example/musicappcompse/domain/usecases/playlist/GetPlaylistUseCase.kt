package com.example.musicappcompse.domain.usecases.playlist

import com.example.musicappcompse.data.PlaylistRepository
import javax.inject.Inject

class GetPlaylistUseCase @Inject constructor(
    private val playlistRepository: PlaylistRepository
) {
    suspend fun invoke(playlistId : Int) = playlistRepository.getPlaylist(playlistId)
}