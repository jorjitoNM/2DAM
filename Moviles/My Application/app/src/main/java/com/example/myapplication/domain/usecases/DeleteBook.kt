package com.example.myapplication.domain.usecases;

import com.example.myapplication.data.Repository
import com.example.myapplication.domain.model.Book;

class DeleteBook {
    operator fun invoke(book : Book) : Boolean {
        return Repository.deleteBook(book)
    }
}