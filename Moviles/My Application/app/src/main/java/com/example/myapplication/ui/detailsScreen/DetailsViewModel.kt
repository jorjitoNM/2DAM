package com.example.myapplication.ui.detailsScreen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.domain.model.Book
import com.example.myapplication.domain.usecases.DeleteBook
import com.example.myapplication.domain.usecases.GetBook
import com.example.myapplication.domain.usecases.GetBooksSize
import com.example.myapplication.domain.usecases.UpdateBook
import com.example.myapplication.ui.common.StringProvider
import com.example.myapplication.ui.common.UiEvent
import com.example.viewmodel.R

class DetailsViewModel(
    private val stringProvider: StringProvider,
    private val updateBookUseCase: UpdateBook,
    private val deleteBookUseCase: DeleteBook,
    private val getBookUseCase: GetBook,
    private val getBooksSize: GetBooksSize,
) : ViewModel() {

    private val _uiState = MutableLiveData(DetailsState())
    val uiState: LiveData<DetailsState> get() = _uiState

    fun handleEvent (event : DetailsEvents) {
        when (event) {
            is DetailsEvents.UpdateBook -> updateBook(event.book)
            is DetailsEvents.DeleteBook -> deleteBook(event.bookId)
            is DetailsEvents.GetBook -> getBook(event.bookId)
            is DetailsEvents.ErrorMostrado -> eventoMostrado()
        }
    }

    private fun updateBook(book: Book) {
        if (!updateBookUseCase(book))
            _uiState.value =
                _uiState.value?.copy(event = UiEvent.ShowSnackbar(stringProvider.getString(R.string.errorUpdateBook)))
        else
            _uiState.value = _uiState.value?.copy(event = UiEvent.PopBackStack)
    }

    private fun getBook(id: Int) {
        val book = getBookUseCase(id)
        if (book.id < 0) {
            _uiState.value =
                _uiState.value?.copy(event = UiEvent.ShowSnackbar(stringProvider.getString(R.string.bookNotFound)))
        } else {
            _uiState.value =
                _uiState.value?.copy(book = book)}
    }

    private fun deleteBook(id: Int) {
        if (!deleteBookUseCase(id))
            _uiState.value = _uiState.value?.copy(
                event = UiEvent.ShowSnackbar(stringProvider.getString(R.string.deleteError))
            )
        else
            _uiState.value = _uiState.value?.copy(event = UiEvent.PopBackStack)
    }

    private fun eventoMostrado() {
        _uiState.value = _uiState.value?.copy(event = null)
    }


    class DetailsMainViewModelFactory(
        private val stringProvider: StringProvider,
        private val updateBook: UpdateBook,
        private val deleteBook: DeleteBook,
        private val getBook: GetBook,
        private val getBookSizeUseCase: GetBooksSize,
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
                ) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}