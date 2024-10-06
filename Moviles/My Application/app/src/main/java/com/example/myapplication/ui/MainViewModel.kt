package com.example.myapplication.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.domain.model.Book
import com.example.myapplication.domain.usecases.AddBook
import com.example.myapplication.domain.usecases.DeleteBook
import com.example.myapplication.domain.usecases.GetBooks
import com.example.myapplication.domain.usecases.UpdateBook
import com.example.myapplication.utils.Constants

class MainViewModel (
    private val addBook: AddBook,
    private val deleteBook: DeleteBook,
    private val getBooks: GetBooks,
    private val updateBook: UpdateBook,
) : ViewModel() {

    private var indice =0
    private val _uiState = MutableLiveData(MainState())
    val uiState: LiveData<MainState> get() = _uiState


    init {
        _uiState.value = MainState(book=getBooks()[0])
    }

    fun addBook(book: Book) {
        if (!addBook(book)) {
            _uiState.value = MainState(
                book = _uiState.value.let{book},
                error = stringProvider.getString(R.string.name),
            )
            _uiState.value = _uiState
                .value?.copy(error = Constants.ERROR)
        }
    }

    fun getBook(id: Int) {
        val books = getBooks()

        if (books.size < id || id < 0) {
            _uiState.value = _uiState.value?.copy(error = "error")

        } else
            _uiState.value = _uiState.value?.copy(book = book[id])
    }

    fun errorMostrado() {
        _uiState.value = _uiState.value?.copy(error = null)
    }
}


class MainViewModelFactory(
    private val addBook: AddBook,
    private val deleteBook: DeleteBook,
    private val getBooks: GetBooks,
    private val updateBook: UpdateBook,

    ) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return MainViewModel(
                addBook,
                deleteBook,
                getBooks,
                updateBook,
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}