package com.example.myapplication.domain.usecases

import com.example.myapplication.data.Repository
import com.example.myapplication.domain.model.Book

class UpdateBook (private val repository : Repository) {
    operator fun invoke (book : Book) : Book {
        return repository.updateBook(book)
    }
}