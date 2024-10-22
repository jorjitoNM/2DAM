package com.example.myapplication.domain.usecases

import com.example.myapplication.data.Repository

class GetBooksSize {
    operator fun invoke() : Int = Repository.getBooksSize()
}