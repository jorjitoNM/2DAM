package com.example.myapplication.domain.usecases

import com.example.myapplication.data.Repository
import com.example.myapplication.domain.model.Book
import javax.inject.Inject

class UpdateBook @Inject constructor() {
    operator fun invoke (book : Book) : Boolean {
        return Repository.updateBook(book)
    }
}