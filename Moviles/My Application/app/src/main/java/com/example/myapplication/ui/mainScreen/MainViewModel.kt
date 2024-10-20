package com.example.myapplication.ui.mainScreen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.domain.usecases.GetBooks

class MainViewModel(
    private val getBooks: GetBooks,
) : ViewModel() {

    private val _uiState = MutableLiveData(MainState())
    val uiState: LiveData<MainState> get() = _uiState

    init {
        getBooks()
    }

    fun getBooks() {
        _uiState.value = _uiState.value?.copy(books = getBooks.invoke())
    }


}


class MainViewModelFactory(

    private val getBooks: GetBooks,

    ) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return MainViewModel(
                getBooks,
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}