package com.example.myapplication.ui.mainScreen

sealed interface MainEvents {
    data object EventDone : MainEvents
    data class GetSongs(val token : String) : MainEvents
}