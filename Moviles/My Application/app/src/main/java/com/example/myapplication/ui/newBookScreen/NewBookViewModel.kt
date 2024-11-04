package com.example.myapplication.ui.newBookScreen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.R
import com.example.myapplication.domain.model.Book
import com.example.myapplication.domain.usecases.AddBook
import com.example.myapplication.domain.usecases.GetID
import com.example.myapplication.ui.common.StringProvider
import com.example.myapplication.ui.common.UiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class NewBookViewModel @Inject constructor  (
    private val addBookUseCase : AddBook,
    private val getID: GetID,
    private val stringProvider: StringProvider,
    ) : ViewModel()
{

    private val _uiState = MutableLiveData(NewBookState())
    val uiState: LiveData<NewBookState> get() = _uiState

    fun handleEvent (event: NewBookEvents) {
        when (event) {
            is NewBookEvents.AddBook -> addBook(event.book)
            is NewBookEvents.Cancel -> cancel()
            is NewBookEvents.EventoMostrado -> eventoMostrado()
        }
    }

    private fun addBook (book: Book) {
        book.id = getId()
        if (!addBookUseCase(book)) {
            _uiState.value = _uiState.value?.copy(
                event = UiEvent.ShowSnackbar(stringProvider.getString(R.string.deleteError))
            )
        }
        else {
            _uiState.value = _uiState.value?.copy(
                event = UiEvent.PopBackStack)
        }
    }

    private fun cancel() {
        _uiState.value = _uiState.value?.copy(event = UiEvent.PopBackStack)
    }

    private fun eventoMostrado() {
        _uiState.value = _uiState.value?.copy(event = null)
    }

    private fun getId () : Int {
        return getID.invoke()
    }
}

class NewBookViewModelFactory(

    private val addBook: AddBook,
    private val getID: GetID,
    private val stringProvider: StringProvider,

    ) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(NewBookViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return NewBookViewModel(
                addBook,
                getID,
                stringProvider,
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}