package com.example.myapplication.ui.detailsScreen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.R
import com.example.myapplication.domain.model.Book
import com.example.myapplication.domain.usecases.DeleteBook
import com.example.myapplication.domain.usecases.GetBook
import com.example.myapplication.domain.usecases.GetBooksSize
import com.example.myapplication.domain.usecases.GetID
import com.example.myapplication.domain.usecases.UpdateBook
import com.example.myapplication.ui.common.StringProvider
import com.example.myapplication.ui.common.UiEvent

class DetailsViewModel(
    private val stringProvider: StringProvider,
    private val updateBookUseCase: UpdateBook,
    private val deleteBookUseCase: DeleteBook,
    private val getBookUseCase: GetBook,
    private val getBooksSize: GetBooksSize,
    private val getId: GetID,
) : ViewModel() {

    private var indice = 0
    private val _uiState = MutableLiveData(DetailsState())
    val uiState: LiveData<DetailsState> get() = _uiState

    fun updateBook(book: Book) {

    }

    fun getBook(id: Int) {
        if (getBooksSize() < id || id < 0) {
            _uiState.value =
                _uiState.value?.copy(mensaje = stringProvider.getString(R.string.bookNotFound))
        } else {
            _uiState.value =
                _uiState.value?.copy(book = getBookUseCase(id))        }
    }

    fun deleteBook(book: Book) {
        if (!deleteBookUseCase(book))
            _uiState.value = _uiState.value?.copy(
                mensaje = stringProvider.getString(R.string.deleteError),
                event = UiEvent.ShowSnackbar(stringProvider.getString(R.string.deleteError))
            )
        /*else if (getBook(indice - 1) != null)
            _uiState.value = getBook(indice - 1)?.let {
                _uiState.value?.copy(
                    book = it,
                    event = UiEvent.PopBackStack
                )
            }*/
    }


    fun errorMostrado() {
        _uiState.value = _uiState.value?.copy(mensaje = null)
    }

    fun showCalendar() {
    }

    fun getId(name: String, author: String): Int {
        return getId.invoke(name, author)
    }


    class DetailsMainViewModelFactory(
        private val stringProvider: StringProvider,
        private val updateBook: UpdateBook,
        private val deleteBook: DeleteBook,
        private val getBook: GetBook,
        private val getBookSizeUseCase: GetBooksSize,
        private val getId: GetID,

        ) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(DetailsViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return DetailsViewModel(
                    stringProvider,
                    updateBook,
                    deleteBook,
                    getBook,
                    getBookSizeUseCase,
                    getId,
                ) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}