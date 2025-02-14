package com.example.musiccompose.data

import com.example.musiccompose.data.local.dao.PlaylistDao
import javax.inject.Inject

class PlaylistRepository @Inject constructor(
    private val playlistDao: PlaylistDao,
) {
    fun getAll() = playlistDao.getAll()
}