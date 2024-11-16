package com.example.myapplication.ui.detailsScreen

sealed interface DetailsEvents {
    class GetSong (val songId : String) : DetailsEvents
    data object ErrorMostrado : DetailsEvents
}