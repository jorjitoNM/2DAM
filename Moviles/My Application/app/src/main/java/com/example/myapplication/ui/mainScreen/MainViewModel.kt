package com.example.myapplication.ui.mainScreen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myapplication.domain.usecases.GetAlbum
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor (
    private val getAlbum: GetAlbum,
) : ViewModel() {

    private val _uiState = MutableLiveData(MainState())
    val uiState: LiveData<MainState> get() = _uiState

    fun handleEvent (event : MainEvents) {
        when (event) {
            is MainEvents.GetBooks -> getBooks()
        }
    }

    private fun getBooks() {
        _uiState.value = _uiState.value?.copy(books = getAlbum.invoke())
    }
}