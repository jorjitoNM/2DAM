package com.example.musicapprest.ui.playlist_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.musicapprest.di.IoDispatcher
import com.example.musicapprest.domain.usecases.playlist.GetAllPlaylistUseCase
import com.example.playlistcompose.ui.playlist_list.PlaylistListEvents
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PlaylistListViewModel @Inject constructor(
    private val getAllPlaylistUseCase: GetAllPlaylistUseCase,
    @IoDispatcher private val dispatcher: CoroutineDispatcher,
) : ViewModel() {
    private val _uiState : MutableStateFlow<PlaylistListState> = MutableStateFlow(PlaylistListState())
    val uiState : StateFlow<PlaylistListState> =_uiState.asStateFlow()

    fun handleEvent (event : PlaylistListEvents) {
        when (event) {
            is PlaylistListEvents.GetAll -> getAllPlaylists()
            is PlaylistListEvents.EventDone -> _uiState.update { it.copy(event = null) }
        }
    }

    private fun getAllPlaylists () {
        viewModelScope.launch(dispatcher) {
            _uiState.update { it.copy(playlists = getAllPlaylistUseCase.invoke()) }
        }
    }
}