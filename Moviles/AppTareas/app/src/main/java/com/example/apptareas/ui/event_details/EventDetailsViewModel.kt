package com.example.apptareas.ui.event_details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.apptareas.data.remote.NetworkResult
import com.example.apptareas.domain.model.Event
import com.example.apptareas.domain.usecases.GetEventUseCase
import com.example.apptareas.domain.usecases.UpdateEventUseCase
import com.example.apptareas.ui.common.UiEvent
import com.example.apptareas.utilities.Constantes
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EventDetailsViewModel @Inject constructor(
    private val updateEventUseCase : UpdateEventUseCase,
    private val getEventUseCase: GetEventUseCase,
) : ViewModel() {
    private val _uiState = MutableLiveData(EventDetailsState())
    val uiState: LiveData<EventDetailsState> get() = _uiState

    fun handleEvent (event : EventDetailsEvents) {
        when (event) {
            is EventDetailsEvents.GetEvent -> getEvent(event.eventId)
            is EventDetailsEvents.UpdateEvent -> updateEvent(event.event)
            is EventDetailsEvents.EventDone -> _uiState.value = _uiState.value?.copy(appEvent = null)
        }
    }

    private fun updateEvent(event: Event) {
        viewModelScope.launch {
            when (updateEventUseCase.invoke(event)) {
                is NetworkResult.Success -> _uiState.value = _uiState.value?.copy(appEvent = UiEvent.ShowSnackbar(Constantes.EVENT_UPDATED))
                is NetworkResult.Loading -> TODO()
                is NetworkResult.Error -> _uiState.value = _uiState.value?.copy(appEvent = UiEvent.ShowSnackbar(
                    Constantes.ERROR_UPDATING_EVENT))
            }
        }
    }

    private fun getEvent(eventId: Int) {
        viewModelScope.launch {
            when (val result = getEventUseCase.invoke(eventId)) {
                is NetworkResult.Success -> _uiState.value = _uiState.value?.copy(event = result.data)
                is NetworkResult.Loading -> TODO()
                is NetworkResult.Error -> _uiState.value = _uiState.value?.copy(appEvent = UiEvent.ShowSnackbar(
                    Constantes.ERROR__GETTING_EVENT))
            }
        }
    }
}