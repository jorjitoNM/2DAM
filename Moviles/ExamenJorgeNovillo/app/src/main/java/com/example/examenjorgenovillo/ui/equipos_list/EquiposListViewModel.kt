package com.example.examenjorgenovillo.ui.equipos_list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.examenjorgenovillo.data.remote.NetworkResult
import com.example.examenjorgenovillo.domain.usecases.GetEquiposUseCase
import com.example.examenjorgenovillo.ui.common.UiEvent
import com.example.examenjorgenovillo.utilities.Constantes
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EquiposListViewModel @Inject constructor(
    private val getEquipos: GetEquiposUseCase,
) : ViewModel() {

    private val _uiState = MutableLiveData(EquiposListState())
    val uiState: LiveData<EquiposListState> get() = _uiState

    fun handleEvent (event : EquiposListEvents) {
        when (event) {
            is EquiposListEvents.getEquipos ->  getEquipos()
            is EquiposListEvents.eventDone -> _uiState.value = _uiState.value?.copy(event = null)
        }
    }
    private fun getEquipos () {
        viewModelScope.launch {
            when (val equipos = getEquipos.invoke()) {
                is NetworkResult.Success -> _uiState.value = _uiState.value?.copy(equipos = equipos.data)
                is NetworkResult.Error -> _uiState.value = _uiState.value?.copy(event = UiEvent.ShowSnackbar(Constantes.ERROR_GETTING_TEAMS))
                is NetworkResult.Loading -> _uiState.value = _uiState.value?.copy(event = UiEvent.ShowSnackbar(Constantes.LOADING))
            }
        }
    }
}