package com.example.myapplication.domain.usecases

import com.example.myapplication.data.Repository
import com.example.myapplication.domain.model.Book
import javax.inject.Inject

class GetBooks @Inject constructor() {
    operator fun invoke () : List<Book> {
        return Repository.getBooks()
    }
}