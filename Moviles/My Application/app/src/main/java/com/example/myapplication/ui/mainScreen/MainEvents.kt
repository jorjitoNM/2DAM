package com.example.myapplication.ui.mainScreen

sealed interface MainEvents {
    data object GetBooks : MainEvents
}