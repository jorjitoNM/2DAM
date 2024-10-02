package com.example.myapplication.ui

import com.example.myapplication.domain.model.Book

data class MainState(
    val book : Book,
    val error : String? = null,
) {
}