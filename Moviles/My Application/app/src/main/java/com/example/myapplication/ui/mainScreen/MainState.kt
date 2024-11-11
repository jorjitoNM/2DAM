package com.example.myapplication.ui.mainScreen

import com.example.myapplication.domain.model.Book

data class MainState(
    val books: List<Book> = emptyList(),
)