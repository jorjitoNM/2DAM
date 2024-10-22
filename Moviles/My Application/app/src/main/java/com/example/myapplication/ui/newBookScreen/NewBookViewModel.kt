package com.example.myapplication.ui.newBookScreen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.R
import com.example.myapplication.domain.model.Book
import com.example.myapplication.domain.usecases.AddBook
import com.example.myapplication.ui.common.StringProvider

class NewBookViewModel  (
    private val addBookUseCase : AddBook,
    private val stringProvider: StringProvider,
    ) : ViewModel()
{

    private val _uiState = MutableLiveData(NewBookState())
    val uiState: LiveData<NewBookState> get() = _uiState

    fun addBook (book: Book) {
        if (!addBookUseCase(book)) {
            _uiState.value =
                _uiState.value?.copy(mensaje = stringProvider.getString(R.string.addError))
        }
    }
}

class NewBookViewModelFactory(

    private val addBook: AddBook,
    private val stringProvider: StringProvider,

    ) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(NewBookViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return NewBookViewModel(
                addBook,
                stringProvider,
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}