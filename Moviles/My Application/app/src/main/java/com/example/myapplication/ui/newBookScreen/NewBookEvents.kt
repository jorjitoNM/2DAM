package com.example.myapplication.ui.newBookScreen

import com.example.myapplication.domain.model.Book

sealed class NewBookEvents {
    class AddBook (val book : Book) : NewBookEvents()
    data object Cancel : NewBookEvents()
    data object EventoMostrado : NewBookEvents()
}