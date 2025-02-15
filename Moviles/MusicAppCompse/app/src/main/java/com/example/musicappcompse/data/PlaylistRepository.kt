package com.example.musicappcompse.data

import com.example.musicappcompse.data.local.dao.PlaylistDao
import com.example.musicappcompse.domain.model.Playlist
import javax.inject.Inject

class PlaylistRepository @Inject constructor(
    private val playlistDao: PlaylistDao,
) {
    fun getAll() = playlistDao.getAll()

    fun getPlaylist(playlistId : Int) = playlistDao.getPlaylist(playlistId)

    fun update(playlist: Playlist) = playlistDao.update(playlist)
}