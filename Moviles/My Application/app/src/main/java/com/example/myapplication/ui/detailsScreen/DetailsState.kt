package com.example.myapplication.ui.detailsScreen

import com.example.myapplication.domain.model.Book

data class DetailsState(
    val book : Book = Book(),
    val mensaje : String? = null,
    val next : Boolean = true,
    val previous : Boolean = false,
)