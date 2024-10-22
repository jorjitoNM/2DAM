package com.example.myapplication.domain.usecases

import com.example.myapplication.data.Repository
import com.example.myapplication.domain.model.Book

class GetBooks {
    operator fun invoke () : List<Book> {
        return Repository.getBooks()
    }
}