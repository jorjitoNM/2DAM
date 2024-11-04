package com.example.myapplication.ui.mainScreen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.domain.usecases.GetBooks
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor (
    private val getBooks: GetBooks,
) : ViewModel() {

    private val _uiState = MutableLiveData(MainState())
    val uiState: LiveData<MainState> get() = _uiState

    fun handleEvent (event : MainEvents) {
        when (event) {
            is MainEvents.GetBooks -> getBooks()
        }
    }

    private fun getBooks() {
        _uiState.value = _uiState.value?.copy(books = getBooks.invoke())
    }
}