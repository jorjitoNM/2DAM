package com.example.myapplication.domain.usecases

import com.example.myapplication.data.Repository

class GetBooksSize (private val repository : Repository) {
    operator fun invoke() : Int = repository.getBooksSize()
}