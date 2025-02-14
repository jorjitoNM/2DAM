package com.example.musicappcompse.data

import com.example.musicappcompse.data.local.dao.SongsDao
import javax.inject.Inject

class SongsRepository @Inject constructor(
    private val songsDao : SongsDao,
) {
}