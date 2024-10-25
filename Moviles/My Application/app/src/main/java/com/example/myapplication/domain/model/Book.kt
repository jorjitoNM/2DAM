package com.example.myapplication.domain.model


data class Book(
    val id : Int = 0,
    val name : String = "",
    val author : String = "Anonymous",
    val score : Float = 0f,
    val releaseDate : Int = 0,
)