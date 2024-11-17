package com.example.myapplication.domain.usecases;

import com.example.myapplication.R
import com.example.myapplication.data.SongsRepository
import com.example.myapplication.data.remote.NetworkResult
import com.example.myapplication.domain.model.Song
import javax.inject.Inject

class GetSong @Inject constructor(private val songsRepository: SongsRepository) {
    suspend operator fun invoke(id : String, token : String) =
        songsRepository.fetchSong(id,"${R.string.bearer.toString()}$token")
}