package com.example.examenjorgenovillo.ui.jugador_details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.examenjorgenovillo.data.remote.NetworkResult
import com.example.examenjorgenovillo.domain.model.Jugador
import com.example.examenjorgenovillo.domain.usecases.AddJugadorUseCase
import com.example.examenjorgenovillo.ui.common.UiEvent
import com.example.examenjorgenovillo.utilities.Constantes
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class JugadoresDetailsViewModel @Inject constructor (
    private val addJugadorUseCase : AddJugadorUseCase,
) : ViewModel() {

    private val _uiState = MutableLiveData(JugadorState())
    val uiState: LiveData<JugadorState> get() = _uiState

    fun handleEvent (event : JugadorDetailsEvents) {
        when (event) {
            is JugadorDetailsEvents.addJugador -> addJugador(event.equipoId,event.jugador)
        }
    }

    private fun addJugador(equipoId: Int, jugador : Jugador) {
        viewModelScope.launch {
            when (val jugador = addJugadorUseCase.invoke(equipoId, jugador)) {
                is NetworkResult.Success -> _uiState.value = _uiState.value?.copy(jugador = jugador.data)
                is NetworkResult.Error -> _uiState.value = _uiState.value?.copy(event = UiEvent.ShowSnackbar(Constantes.ERROR_ADDING_PLAYER))
                is NetworkResult.Loading -> _uiState.value = _uiState.value?.copy(event = UiEvent.ShowSnackbar(
                    Constantes.LOADING))
            }
        }
    }
}