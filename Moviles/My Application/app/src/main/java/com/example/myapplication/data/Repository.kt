package com.example.myapplication.data

import com.example.myapplication.domain.model.Book

class Repository {

    companion object {
        private val books = mutableListOf<Book>()
        fun getInstance(): Repository = Repository()
    }

    fun addBook (book : Book) = books.add(book)

    fun getBooks() : List<Book> {
        return books
    }
    fun updateBook (book : Book, ) = books.stream().filter()
}