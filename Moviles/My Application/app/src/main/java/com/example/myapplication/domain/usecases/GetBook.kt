package com.example.myapplication.domain.usecases;

import com.example.myapplication.data.Repository;
import com.example.myapplication.domain.model.Book

class GetBook {
    operator fun invoke(id : Int) : Book = Repository.getBook(id)
}