package com.example.myapplication.data

import com.example.myapplication.domain.model.Book

class Repository {

    companion object {
        private val books = mutableListOf<Book>()
        fun getInstance(): Repository = Repository()
    }

    init {
        books.add(Book("La biblia","Anonimo",2,0))
        books.add(Book("La biblia 2","Anonimo",1,1000))
        books.add(Book("La biblia 3","Anonimo",3,2000))
    }

    fun addBook (book : Book) = books.add(book)

    fun getBooks() : List<Book> {
        return books
    }
    fun updateBook (book : Book, ) : Book = books.stream().filter(b -> b.);
    fun deleteBook(book : Book): Boolean = books.remove(book)
}