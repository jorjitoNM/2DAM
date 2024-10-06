package com.example.myapplication.domain.usecases;

import com.example.myapplication.data.Repository;
import com.example.myapplication.domain.model.Book

class GetBooks (private val repository : Repository) {
    operator fun invoke() : List<Book> = repository.getBooks()
}