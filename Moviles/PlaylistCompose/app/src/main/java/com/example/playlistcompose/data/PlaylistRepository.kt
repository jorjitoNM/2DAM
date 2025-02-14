package com.example.playlistcompose.data

import com.example.playlistcompose.data.local.dao.PlaylistDao
import javax.inject.Inject

class PlaylistRepository @Inject constructor(
    private val playlistDao: PlaylistDao,
) {
    fun getAll() = playlistDao.getAll()
}