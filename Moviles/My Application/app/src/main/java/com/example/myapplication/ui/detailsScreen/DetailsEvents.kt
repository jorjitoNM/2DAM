package com.example.myapplication.ui.detailsScreen

import com.example.myapplication.domain.model.Book

sealed interface DetailsEvents {
    class UpdateBook (val book : Book) : DetailsEvents
    class DeleteBook (val bookId : Int) : DetailsEvents
    class GetBook (val bookId : Int) : DetailsEvents
    data object ErrorMostrado : DetailsEvents
}