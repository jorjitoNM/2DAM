package com.example.myapplication.domain.model

data class Song(
    val name : String,
    val artist : String,
    val duration : Int,
    val explicit : Boolean,
    val albumImage : String,
)
