package com.example.myapplication.domain.model


data class Book(
    var name : String = "",
    var author : String = "Anonymous",
    var score : Float = 0f,
    var releaseDate : Int = 0,
) {
}