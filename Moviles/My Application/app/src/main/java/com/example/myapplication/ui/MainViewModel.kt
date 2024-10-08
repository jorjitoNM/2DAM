package com.example.myapplication.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.R
import com.example.myapplication.domain.model.Book
import com.example.myapplication.domain.usecases.AddBook
import com.example.myapplication.domain.usecases.DeleteBook
import com.example.myapplication.domain.usecases.GetBook
import com.example.myapplication.domain.usecases.GetBooksSize
import com.example.myapplication.domain.usecases.UpdateBook
import com.example.myapplication.utils.StringProvider
import com.google.android.material.datepicker.MaterialDatePicker

class MainViewModel (
    private val stringProvider: StringProvider,
    private val addBookUseCase: AddBook,
    private val deleteBookUseCase: DeleteBook,
    private val getBookUseCase: GetBook,
    private val updateBookUseCase: UpdateBook,
    private val getBooksSizeUseCase : GetBooksSize,
) : ViewModel() {

    private var indice = 0
    private val _uiState = MutableLiveData(MainState())
    val uiState: LiveData<MainState> get() = _uiState


    init {
        _uiState.value = MainState(book=Book())
    }


    /*fun addBook(book: Book) {
        if (!addBookUseCase(book)) {
            _uiState.value = MainState(
                book = _uiState.value.let{book},
                mensaje = stringProvider.getString(R.string.addError),
            )
            _uiState.value = _uiState
                .value?.copy(mensaje = Constants.ERROR)
        }
    }*/
    fun addBook(book: Book) {
        if (!addBookUseCase(book)) {
            _uiState.value = _uiState.value?.copy(mensaje = stringProvider.getString(R.string.addError))
        } else
            previous()
    }

    fun updateBook (book : Book) {
        _uiState.value = _uiState.value?.copy(book = updateBookUseCase(book))
    }

    private fun getBook(id: Int) : Book? {
        if (getBooksSizeUseCase() < id || id < 0) {
            _uiState.value = _uiState.value?.copy(mensaje = stringProvider.getString(R.string.bookNotFound))
            return null
        } else
            return getBookUseCase(id)
    }

    fun deleteBook(book : Book) {
        if (!deleteBookUseCase(book))
            _uiState.value = _uiState.value?.copy(mensaje = stringProvider.getString(R.string.deleteError))
        else if (getBook(indice-1) != null)
            _uiState.value = getBook(indice-1)?.let { _uiState.value?.copy(book = it) }
    }


    fun errorMostrado() {
        _uiState.value = _uiState.value?.copy(mensaje = null)
    }


    fun previous() {
        if ((indice-1) == 0)
            _uiState.value = _uiState.value?.copy(previous = false)
        if (getBook(indice-1) == null)
            _uiState.value = _uiState.value?.copy(mensaje = stringProvider.getString(R.string.errorAnterior))
        else {
            indice--
            _uiState.value = getBook(indice)?.let {
                _uiState.value?.copy(
                    previous = true,
                    book = it
                )
            }
        }
    }
    fun next() {
        if ((indice+1) == getBooksSizeUseCase()-1)
            _uiState.value = _uiState.value?.copy(next = false)
        if (getBook(indice+1) == null)
            _uiState.value = _uiState.value?.copy(mensaje = stringProvider.getString(R.string.errorSiguiente))
        else {
            indice++
            _uiState.value = getBook(indice)?.let {
                _uiState.value?.copy(
                    next = true,
                    book = it
                )
            }
        }
    }

    fun showCalendar() {
        val datePicker =
            MaterialDatePicker.Builder.datePicker()
                .setTitleText(stringProvider.getString(R.string.selectDate))
                .build()
    }
}


class MainViewModelFactory(
    private val stringProvider: StringProvider,
    private val addBook: AddBook,
    private val deleteBook: DeleteBook,
    private val getBook: GetBook,
    private val updateBook: UpdateBook,
    private val getBookSizeUseCase : GetBooksSize,

    ) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return MainViewModel(
                stringProvider,
                addBook,
                deleteBook,
                getBook,
                updateBook,
                getBookSizeUseCase,
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}