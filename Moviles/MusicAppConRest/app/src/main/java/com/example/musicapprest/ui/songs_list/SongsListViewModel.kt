package com.example.musicapprest.ui.songs_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.musicapprest.common.NetworkResult
import com.example.musicapprest.di.IoDispatcher
import com.example.musicapprest.domain.usecases.song.GetAllSongsUseCase
import com.example.primeraapp.ui.common.UiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SongsListViewModel @Inject constructor(
    private val getAllSongsUseCase: GetAllSongsUseCase,
    @IoDispatcher private val dispatcher: CoroutineDispatcher,
) : ViewModel() {

    private val _uiState: MutableStateFlow<SongsListState> = MutableStateFlow(SongsListState())
    val uiState: StateFlow<SongsListState> = _uiState.asStateFlow()


    fun handleEvent(event: SongsListEvents) {
        when (event) {
            is SongsListEvents.GetAll -> getAllSongs()
            is SongsListEvents.EventDone -> _uiState.update { it.copy(event = null) }
        }
    }

    private fun getAllSongs() {
        viewModelScope.launch(dispatcher) {
            when (val result = getAllSongsUseCase.invoke()) {
                is NetworkResult.Success -> _uiState.update {
                    it.copy(
                        songs = result.data
                    )
                }

                is NetworkResult.Error -> _uiState.update {
                    it.copy(
                        event = UiEvent.ShowSnackbar(result.message),
                    )
                }

                is NetworkResult.Loading -> TODO()
            }
        }
    }
}