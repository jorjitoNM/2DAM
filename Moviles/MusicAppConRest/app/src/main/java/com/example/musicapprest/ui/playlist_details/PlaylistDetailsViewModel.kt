package com.example.musicapprest.ui.playlist_details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.musicapprest.R
import com.example.musicapprest.common.NetworkResult
import com.example.musicapprest.common.StringProvider
import com.example.musicapprest.di.IoDispatcher
import com.example.musicapprest.domain.model.Playlist
import com.example.musicapprest.domain.usecases.playlist.DeletePlaylistUseCase
import com.example.musicapprest.domain.usecases.playlist.GetPlaylistUseCase
import com.example.musicapprest.domain.usecases.playlist.UpdatePlaylistUseCase
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
class PlaylistDetailsViewModel @Inject constructor(
    private val getPlaylistUseCase: GetPlaylistUseCase,
    @IoDispatcher private val dispatcher: CoroutineDispatcher,
    private val stringProvider: StringProvider,
    private val updatePlaylistUseCase: UpdatePlaylistUseCase,
    private val deletePlaylistUseCase: DeletePlaylistUseCase,
) : ViewModel() {
    private val _uiState: MutableStateFlow<PlaylistDetailsState> =
        MutableStateFlow(PlaylistDetailsState())
    val uiState: StateFlow<PlaylistDetailsState> = _uiState.asStateFlow()

    fun handleEvent(event: PlaylistDetailsEvents) {
        when (event) {
            is PlaylistDetailsEvents.GetPlaylist -> getPlaylist(event.playlistId)
            is PlaylistDetailsEvents.EventDone -> _uiState.update { it.copy(event = null) }
            is PlaylistDetailsEvents.OnPlaylistNameChanged -> _uiState.update { currentState ->
                currentState.copy(
                    playlist = Playlist(
                        _uiState.value.playlist.playlistId,
                        event.playlistName,
                        _uiState.value.playlist.songs,
                        _uiState.value.playlist.owner
                    )
                )
            }

            is PlaylistDetailsEvents.UpdatePlaylist -> update()
            is PlaylistDetailsEvents.DeletePlaylist -> delete()
        }
    }

    private fun update() {
        viewModelScope.launch(dispatcher) {
            when (val result = updatePlaylistUseCase.invoke(_uiState.value.playlist)) {
                is NetworkResult.Success -> {
                    _uiState.update {
                        it.copy(
                            event =
                            UiEvent.ShowSnackbar(stringProvider.getString(R.string.updated_successfully))
                        )
                    }
                    getPlaylist(result.data.playlistId)
                }

                is NetworkResult.Error -> _uiState.update {
                    it.copy(
                        event =
                        UiEvent.ShowSnackbar(
                            stringProvider.getString(R.string.error_updating)
                        )
                    )
                }

                is NetworkResult.Loading -> TODO()
            }
        }
    }

    private fun delete() {
        viewModelScope.launch(dispatcher) {
            when (val result = deletePlaylistUseCase.invoke(_uiState.value.playlist.playlistId)) {
                is NetworkResult.Success -> {
                    _uiState.update {
                        it.copy(
                            event =
                            UiEvent.PopBackStack
                        )
                    }
                }

                is NetworkResult.Error -> _uiState.update {
                    it.copy(
                        event =
                        UiEvent.ShowSnackbar(
                            stringProvider.getString(R.string.error_deleting) + ": " + result.message
                        )
                    )
                }

                is NetworkResult.Loading -> TODO()
            }
        }
    }

    private fun getPlaylist(playlistId: Int) {
        viewModelScope.launch(dispatcher) {
            when (val result = getPlaylistUseCase.invoke(playlistId)) {
                is NetworkResult.Success -> _uiState.update {
                    it.copy(playlist = result.data)
                }

                is NetworkResult.Error -> _uiState.update {
                    it.copy(event = UiEvent.ShowSnackbar(result.message))
                }

                is NetworkResult.Loading -> TODO()
            }
        }
    }
}