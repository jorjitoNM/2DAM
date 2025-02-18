package com.example.musicapprest.domain.model

import com.google.gson.annotations.SerializedName


data class Song(
    @SerializedName("songId")
    val songId: Int = 0,
    @SerializedName("songName")
    val songName: String = "",
    @SerializedName("artist")
    val artist: String = "",
)