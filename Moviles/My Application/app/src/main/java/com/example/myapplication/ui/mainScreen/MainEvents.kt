package com.example.myapplication.ui.mainScreen

sealed class MainEvents {
    data object GetBooks : MainEvents()
}