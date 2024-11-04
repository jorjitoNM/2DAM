package com.example.myapplication.ui.newBookScreen

import com.example.myapplication.domain.model.Book
import com.example.myapplication.ui.common.UiEvent

data class NewBookState (
    val book : Book = Book(),
    val event : UiEvent? = null,
)