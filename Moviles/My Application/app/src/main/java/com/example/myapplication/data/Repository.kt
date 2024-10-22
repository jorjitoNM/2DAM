package com.example.myapplication.data

import com.example.myapplication.domain.model.Book

object Repository {


    private val books = mutableListOf<Book>()

    init {
        books.add(Book(1,"La biblia","Anonimo",2f,0))
        books.add(Book(2,"La biblia 2","Anonimo",1f,1000))
        books.add(Book(3,"La biblia 3","Anonimo",3f,2000))
    }

    fun getBooks () : List<Book> {
        return books.toList()
    }

    fun addBook (book : Book) : Boolean {
        if (books.any{b -> b.name != book.name && b.author != book.author})
            return books.add(book)
        else
            return false;
    }

    fun getBook(id : Int) : Book {
        return books[id]
    }
    fun updateBook (book : Book) : Book {
        var foundBook = books.first { b ->
            b.name == book.name && b.author == book.author
        }
        foundBook = Book(foundBook.id,book.name,book.author,book.score,book.releaseDate)
        return foundBook
    }

    fun deleteBook(book : Book): Boolean {
        return books.removeIf { b -> b.name == book.name && b.author == book.author}
    }

    fun getBooksSize(): Int {
        return books.size
    }

    fun getId(name: String, author: String) : Int {
        return books.first { b -> b.name == name && b.author == author }.id
    }
}