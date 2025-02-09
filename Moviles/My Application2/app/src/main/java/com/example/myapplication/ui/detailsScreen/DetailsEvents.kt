package com.example.myapplication.ui.detailsScreen

sealed interface DetailsEvents {
    class GetCharacter (val characterId : Int) : DetailsEvents
    data object EventDone : DetailsEvents
}