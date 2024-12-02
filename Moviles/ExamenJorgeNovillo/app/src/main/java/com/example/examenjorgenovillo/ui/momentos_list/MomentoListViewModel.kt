package com.example.examenjorgenovillo.ui.momentos_list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.examenjorgenovillo.data.remote.NetworkResult
import com.example.examenjorgenovillo.domain.usecases.GetMomentosUseCase
import com.example.examenjorgenovillo.ui.common.UiEvent
import com.example.examenjorgenovillo.utilities.Constantes
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MomentoListViewModel @Inject constructor(
    private val getMomentosUseCase: GetMomentosUseCase,
) : ViewModel() {

    private val _uiState = MutableLiveData(MomentosListState())
    val uiState: LiveData<MomentosListState> get() = _uiState

    fun handleEvent(event: MomentoListEvents) {
        when (event) {
            is MomentoListEvents.getMomentos -> getMomentos(event.equipoId)
            is MomentoListEvents.eventDone -> _uiState.value = _uiState.value?.copy(event = null)
        }
    }

    private fun getMomentos(equipoId: Int) {
        viewModelScope.launch {
            when (val momentos = getMomentosUseCase.invoke(equipoId)) {
                is NetworkResult.Success -> _uiState.value =
                    _uiState.value?.copy(momentos = momentos.data)

                is NetworkResult.Error -> _uiState.value =
                    _uiState.value?.copy(event = UiEvent.ShowSnackbar(Constantes.ERROR_GETTING_MOMENTS))

                is NetworkResult.Loading -> _uiState.value =
                    _uiState.value?.copy(event = UiEvent.ShowSnackbar(Constantes.LOADING))
            }
        }
    }
}