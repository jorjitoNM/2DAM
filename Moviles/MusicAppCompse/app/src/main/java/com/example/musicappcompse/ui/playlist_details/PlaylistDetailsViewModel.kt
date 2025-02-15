package com.example.musicappcompse.ui.playlist_details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.musicappcompse.R
import com.example.musicappcompse.common.StringProvider
import com.example.musicappcompse.data.DataStoreRepository
import com.example.musicappcompse.domain.model.Playlist
import com.example.musicappcompse.domain.usecases.playlist.GetPlaylistUseCase
import com.example.musicappcompse.domain.usecases.playlist.UpdatePlaylistUseCase
import com.example.playlistcompose.di.IoDispatcher
import com.example.primeraapp.ui.common.UiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class PlaylistDetailsViewModel @Inject constructor(
    private val getPlaylistUseCase: GetPlaylistUseCase,
    @IoDispatcher private val dispatcher: CoroutineDispatcher,
    private val stringProvider: StringProvider,
    private val dataStoreRepository: DataStoreRepository,
    private val updatePlaylistUseCase: UpdatePlaylistUseCase,
) : ViewModel() {
    private val _uiState: MutableStateFlow<PlaylistDetailsState> =
        MutableStateFlow(PlaylistDetailsState())
    val uiState: StateFlow<PlaylistDetailsState> = _uiState.asStateFlow()

    private val userName: StateFlow<String> = dataStoreRepository.userName
        .stateIn(
            scope = CoroutineScope(dispatcher),
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = "Yotoko Tutoto"
        )

    fun handleEvent(event: PlaylistDetailsEvents) {
        when (event) {
            is PlaylistDetailsEvents.GetPlaylist -> getPlaylist(event.playlistId)
            is PlaylistDetailsEvents.EventDone -> _uiState.update { it.copy(event = null) }
            is PlaylistDetailsEvents.GetUserName -> _uiState.update { it.copy(userName = userName.value) }
            is PlaylistDetailsEvents.OnPlaylistNameChanged -> _uiState.update { currentState ->
                currentState.copy(
                    playlist = currentState.playlist.copy(
                        playlist = Playlist(
                            _uiState.value.playlist.playlist.playlistId,
                            event.playlistName
                        )
                    )
                )
            }

            is PlaylistDetailsEvents.UpdatePlaylist -> update()
        }
    }

    private fun update() {
        try {
            viewModelScope.launch(dispatcher) {
                    if (updatePlaylistUseCase.invoke(_uiState.value.playlist.playlist)) {
                        _uiState.update {
                            it.copy(
                                event =
                                UiEvent.ShowSnackbar(stringProvider.getString(R.string.updated_successfully))
                            )
                        }
                        getPlaylist(_uiState.value.playlist.playlist.playlistId.toInt())
                    }
                    else
                        _uiState.update {
                            it.copy(
                                event =
                                UiEvent.ShowSnackbar(stringProvider.getString(R.string.error_updating)
                                )
                            )
                        }
            }
        } catch (e: Exception) {
            _uiState.update {
                it.copy(
                    event =
                    UiEvent.ShowSnackbar(
                        e.message ?: stringProvider.getString(R.string.global_error)
                    )
                )
            }
        }
    }

    private fun getPlaylist(playlistId: Int) {
        try {
            viewModelScope.launch(dispatcher) {
                _uiState.update {
                    it.copy(playlist = getPlaylistUseCase.invoke(playlistId))
                }
            }
        } catch (e: Exception) {
            Timber.e(e.message, e)
            _uiState.update {
                it.copy(
                    event =
                    UiEvent.ShowSnackbar(
                        e.message ?: stringProvider.getString(R.string.global_error)
                    )
                )
            }
        }
    }
}