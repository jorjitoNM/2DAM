package com.example.myapplication.domain.usecases;

import com.example.myapplication.data.Repository
import com.example.myapplication.domain.model.Book;

class DeleteBook (private val repository : Repository) {
    operator fun invoke(book : Book) : Boolean {
        return repository.deleteBook()
    }
}