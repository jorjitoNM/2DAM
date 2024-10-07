package com.example.myapplication.data

import com.example.myapplication.domain.model.Book

class Repository {

    companion object {
        private val books = mutableListOf<Book>()
        fun getInstance(): Repository = Repository()
    }

    init {
        books.add(Book("La biblia","Anonimo",2f,0))
        books.add(Book("La biblia 2","Anonimo",1f,1000))
        books.add(Book("La biblia 3","Anonimo",3f,2000))
    }

    fun addBook (book : Book) {
        if (books.any{b -> b.name != book.name && b.author != book.author})
            books.add(book)
    }

    fun getBooks() : List<Book> {
        return books
    }
    fun updateBook (book : Book) : Book {
        val foundBook = books.first { b ->
            b.name == book.name && b.author == book.author
        }
        foundBook.name = book.name
        foundBook.author = book.author
        foundBook.score = book.score
        foundBook.releaseDate = book.releaseDate
        return foundBook
    }

    fun deleteBook(book : Book): Boolean {
        return books.removeIf { b -> b.name == book.name && b.author == book.author}
    }
}