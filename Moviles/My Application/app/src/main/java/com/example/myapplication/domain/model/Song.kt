package com.example.myapplication.domain.model

import com.example.myapplication.data.remote.model.Artists

data class Song(
    val name: String,
    val artist: ArrayList<Artists>,
    val duration: Int,
    val explicit: Boolean,
    val albumImage: String,
)
