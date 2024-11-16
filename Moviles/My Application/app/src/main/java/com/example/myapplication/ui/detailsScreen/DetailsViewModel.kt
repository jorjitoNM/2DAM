package com.example.myapplication.ui.detailsScreen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.R
import com.example.myapplication.domain.usecases.GetSong
import com.example.myapplication.ui.common.StringProvider
import com.example.myapplication.ui.common.UiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor (
    private val stringProvider: StringProvider,
    private val getSongUseCase: GetSong,
) : ViewModel() {

    private val _uiState = MutableLiveData(DetailsState())
    val uiState: LiveData<DetailsState> get() = _uiState

    fun handleEvent (event : DetailsEvents) {
        when (event) {
            is DetailsEvents.GetSong -> getSong(event.songId)
            is DetailsEvents.ErrorMostrado -> eventoMostrado()
        }
    }


    private fun getSong(id: String) {
        viewModelScope.launch {
            val song = getSongUseCase(id)
            if (song.id < 0) {
                _uiState.value =
                    _uiState.value?.copy(event = UiEvent.ShowSnackbar(stringProvider.getString(R.string.bookNotFound)))
            } else {
                _uiState.value =
                    _uiState.value?.copy(song = song)
            }
        }
    }

    private fun eventoMostrado() {
        _uiState.value = _uiState.value?.copy(event = null)
    }
}