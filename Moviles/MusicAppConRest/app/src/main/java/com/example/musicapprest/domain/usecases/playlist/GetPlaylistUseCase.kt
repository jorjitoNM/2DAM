package com.example.musicapprest.domain.usecases.playlist

import com.example.musicapprest.data.PlaylistRepository
import javax.inject.Inject

class GetPlaylistUseCase @Inject constructor(
    private val playlistRepository: PlaylistRepository
) {
    suspend fun invoke(playlistId : Int) = playlistRepository.getPlaylist(playlistId)
}