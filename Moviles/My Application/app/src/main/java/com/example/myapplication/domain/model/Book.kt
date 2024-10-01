package com.example.myapplication.domain.model


data class Book(
    val name : String = "",
    val author : String = "Anonymus",
    val score : Float = 0f,
    val releaseDate : Int = 0,
) {
}