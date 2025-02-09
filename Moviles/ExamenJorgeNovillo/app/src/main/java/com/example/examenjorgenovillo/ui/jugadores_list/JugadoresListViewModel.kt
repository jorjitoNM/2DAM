package com.example.examenjorgenovillo.ui.jugadores_list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.examenjorgenovillo.data.remote.NetworkResult
import com.example.examenjorgenovillo.domain.usecases.GetJugadoresUseCase
import com.example.examenjorgenovillo.ui.common.UiEvent
import com.example.examenjorgenovillo.utilities.Constantes
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class JugadoresListViewModel @Inject constructor(
    private val getJugadoresUseCase: GetJugadoresUseCase,
) : ViewModel() {

    private val _uiState = MutableLiveData(JugadoresListState())
    val uiState: LiveData<JugadoresListState> get() = _uiState

    fun handleEvent (event : JugadoresListEvents) {
        when (event) {
            is JugadoresListEvents.getJugadores -> getJugadores(event.equipoId)
            is JugadoresListEvents.eventDone -> _uiState.value = _uiState.value?.copy(event = null)
        }
    }

    private fun getJugadores (equipoId : Int) {
        viewModelScope.launch {
            when (val jugadores = getJugadoresUseCase.invoke(equipoId)) {
                is NetworkResult.Success -> _uiState.value = _uiState.value?.copy(jugadores = jugadores.data)
                is NetworkResult.Error -> _uiState.value = _uiState.value?.copy(event = UiEvent.ShowSnackbar(Constantes.ERROR_GETTING_PLAYERS))
                is NetworkResult.Loading -> _uiState.value = _uiState.value?.copy(event = UiEvent.ShowSnackbar(
                    Constantes.LOADING))
            }
        }
    }

}