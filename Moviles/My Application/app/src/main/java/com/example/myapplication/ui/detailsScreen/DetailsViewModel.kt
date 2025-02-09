package com.example.myapplication.ui.detailsScreen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.R
import com.example.myapplication.data.remote.NetworkResult
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
            is DetailsEvents.GetSong -> getSong(event.songId, event.token)
            is DetailsEvents.EventDone -> eventDone()
        }
    }


    private fun getSong(id: String, token : String) {
        viewModelScope.launch {
            when (val song = getSongUseCase(id,token)) {
                is NetworkResult.Error -> _uiState.value =
                    _uiState.value?.copy(event = UiEvent.ShowSnackbar(stringProvider.getString(R.string.songNotFound)))
                is NetworkResult.Loading -> TODO()
                is NetworkResult.Success -> _uiState.value = song.data.let { _uiState.value?.copy(song = it) }
            }
        }
    }

    private fun eventDone() {
        _uiState.value = _uiState.value?.copy(event = null)
    }
}