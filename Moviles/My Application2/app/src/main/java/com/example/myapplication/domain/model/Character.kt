package com.example.myapplication.domain.model

data class Character (
    val id : Int = 0,
    val name : String = "Ricky",
    val alive : Boolean = false,
    val species : String = "Human",
    val image : String = "https://thispersondoesnotexist.com/",
)