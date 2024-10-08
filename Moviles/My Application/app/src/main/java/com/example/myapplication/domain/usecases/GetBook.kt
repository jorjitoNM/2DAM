package com.example.myapplication.domain.usecases;

import com.example.myapplication.data.Repository;
import com.example.myapplication.domain.model.Book

class GetBook (private val repository : Repository) {
    operator fun invoke(id : Int) : Book = repository.getBook(id)
}