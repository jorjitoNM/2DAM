package com.example.myapplication.domain.usecases;

import com.example.myapplication.data.Repository

class DeleteBook {
    operator fun invoke(id : Int) : Boolean {
        return Repository.deleteBook(id)
    }
}