package com.example.musicapprest.data

import com.example.musicapprest.data.remote.datasource.PlaylistsDataSource
import com.example.musicapprest.domain.model.Playlist
import javax.inject.Inject

class PlaylistRepository @Inject constructor(
    private val playlistsDataSource: PlaylistsDataSource,
) {
    suspend fun getAll() = playlistsDataSource.getAll()

    suspend fun getPlaylist(playlistId : Int) = playlistsDataSource.get(playlistId)

    suspend fun update(playlist: Playlist) = playlistsDataSource.update(playlist)
}