package com.example.myapplication.ui.detailsScreen

import com.example.myapplication.domain.model.Book
import com.example.myapplication.ui.common.UiEvent

data class DetailsState(
    val book : Book = Book(),
    val event : UiEvent? = null,
)