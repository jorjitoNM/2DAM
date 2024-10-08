package com.example.myapplication.ui

import com.example.myapplication.domain.model.Book

data class MainState(
    val book : Book = Book(),
    val mensaje : String? = null,
    val next : Boolean = true,
    val previous : Boolean = false,
)