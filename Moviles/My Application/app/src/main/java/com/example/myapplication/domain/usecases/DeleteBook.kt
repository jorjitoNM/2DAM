package com.example.myapplication.domain.usecases;

import com.example.myapplication.data.Repository
import javax.inject.Inject

class DeleteBook @Inject constructor() {
    operator fun invoke(id : Int) : Boolean {
        return Repository.deleteBook(id)
    }
}