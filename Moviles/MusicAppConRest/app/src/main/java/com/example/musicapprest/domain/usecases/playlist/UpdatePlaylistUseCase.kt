package com.example.musicapprest.domain.usecases.playlist

import com.example.musicapprest.common.NetworkResult
import com.example.musicapprest.data.PlaylistRepository
import com.example.musicapprest.domain.model.Playlist
import javax.inject.Inject

class UpdatePlaylistUseCase @Inject constructor(
    private val playlistRepository: PlaylistRepository
){
    suspend fun invoke (playlist : Playlist) : NetworkResult<Playlist> = playlistRepository.update(playlist)
}