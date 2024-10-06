package com.example.myapplication.domain.model


data class Book(
    val name : String = "",
    val author : String = "Anonymus",
    val score : Int = 0,
    val releaseDate : Int = 0,
) {
}