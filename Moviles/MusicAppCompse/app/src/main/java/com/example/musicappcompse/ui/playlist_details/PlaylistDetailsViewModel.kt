package com.example.musicappcompse.ui.playlist_details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.musicappcompse.domain.usecases.playlist.GetPlaylistUseCase
import com.example.playlistcompose.di.IoDispatcher
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PlaylistDetailsViewModel @Inject constructor(
    private val getPlaylistUseCase: GetPlaylistUseCase,
    @IoDispatcher private val dispatcher: CoroutineDispatcher,
) : ViewModel() {
    private val _uiState : MutableStateFlow<PlaylistDetailsState> = MutableStateFlow(PlaylistDetailsState())
    val uiState : StateFlow<PlaylistDetailsState> = _uiState.asStateFlow()

    fun handleEvent (event : PlaylistDetailsEvents) {
        when (event) {
            is PlaylistDetailsEvents.GetPlaylist -> getPlaylist(event.playlistId)
            is PlaylistDetailsEvents.EventDone -> _uiState.update { it.copy(event = null) }
        }
    }

    private fun getPlaylist (playlistId : Int) {
        viewModelScope.launch(dispatcher) {
            _uiState.update {
                it.copy(playlist = getPlaylistUseCase.invoke(playlistId))
            }
        }
    }
}