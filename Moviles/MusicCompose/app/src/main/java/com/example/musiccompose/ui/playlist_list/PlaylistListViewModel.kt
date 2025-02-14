package com.example.musiccompose.ui.playlist_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.musiccompose.di.IoDispatcher
import com.example.musiccompose.domain.usecases.playlist.GetAllPlaylistUseCase
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
            is PlaylistListEvents.GetAll -> getAllMedications()
            is PlaylistListEvents.EventDone -> _uiState.update { it.copy(event = null) }
        }
    }

    private fun getAllMedications () {
        viewModelScope.launch(dispatcher) {
            _uiState.update { it.copy(playlists = getAllPlaylistUseCase.invoke()) }
        }
    }
}