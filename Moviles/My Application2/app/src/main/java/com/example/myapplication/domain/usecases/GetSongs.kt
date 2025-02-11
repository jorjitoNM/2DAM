package com.example.myapplication.domain.usecases

import com.example.myapplication.R
import com.example.myapplication.data.SongsRepository
import com.example.myapplication.data.remote.NetworkResult
import com.example.myapplication.domain.model.Song
import javax.inject.Inject

class GetSongs @Inject constructor(private val songsRepository: SongsRepository) {
    suspend operator fun invoke(token : String) =
        songsRepository.fetchAlbum("${R.string.bearer}$token")
}