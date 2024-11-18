package com.example.myapplication.ui.detailsScreen

sealed interface DetailsEvents {
    class GetSong (val songId : String, val token : String) : DetailsEvents
    data object EventDone : DetailsEvents
}