package com.example.musicappcompse.domain.usecases.playlist

import com.example.musicappcompse.data.PlaylistRepository
import com.example.musicappcompse.domain.model.Playlist
import javax.inject.Inject

class UpdatePlaylistUseCase @Inject constructor(
    private val playlistRepository: PlaylistRepository
){
    fun invoke (playlist : Playlist) : Boolean {
        return playlistRepository.update(playlist) == 1
    }
}