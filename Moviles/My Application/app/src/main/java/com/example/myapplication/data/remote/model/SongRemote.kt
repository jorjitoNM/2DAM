package com.example.myapplication.data.remote.model

data class SongRemote(
    val album : String,
    val artist : String,
    val duration : Int,
    val explicit : Boolean,
    val name : String,
    val albumImage : String,
) {
}