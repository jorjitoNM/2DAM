package com.example.myapplication.data

import com.example.myapplication.domain.model.Book

object Repository {


    private val books = mutableListOf<Book>()
    private var id : Int = 3

    init {
        books.add(Book(0,"La biblia","Anonimo",2f,"01/01/01"))
        books.add(Book(1,"La biblia 2","Anonimo",1f,"01/01/1000"))
        books.add(Book(2,"La biblia 3","Anonimo",3f,"01/01/2000"))
    }

    fun getBooks () : List<Book> {
        return books.toList()
    }

    fun addBook (book : Book) : Boolean {
        if (books.any{b -> b.name != book.name && b.author != book.author}) {
            id++
            return books.add(book)
        }
        else
            return false;
    }

    fun getBook(id : Int) : Book {
        val book : Book
        if (books.any{b -> b.id == id})
            book = books.first{b -> b.id == id}
        else
            book = Book(-1)
        return book
    }
    fun updateBook (book : Book) : Boolean {
        if (books.any {b -> b.id == book.id}){
            books[books.indexOf(books.find { b -> b.id == book.id })] = book
            return true;
        }
        else return false;
    }

    fun deleteBook(id : Int): Boolean {
        return books.removeIf { b -> b.id == id}
    }

    fun getId() : Int {
        return id
    }
}