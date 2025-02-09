package com.example.myapplication.domain.model

import com.example.myapplication.data.remote.model.Artists

data class Song(
    val id : String = "",
    val name: String = "song",
    val artist: List<String> = emptyList(),
    val duration: Int = 0,
    val explicit: Boolean = false,
    val albumImage: String = "https://thispersondoesnotexist.com/",
)
