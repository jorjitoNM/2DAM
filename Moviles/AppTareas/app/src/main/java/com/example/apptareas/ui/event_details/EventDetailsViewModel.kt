package com.example.apptareas.ui.event_details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.apptareas.data.remote.NetworkResult
import com.example.apptareas.di.IoDispatcher
import com.example.apptareas.domain.model.Event
import com.example.apptareas.domain.usecases.events_usercases.GetEventUseCase
import com.example.apptareas.domain.usecases.events_usercases.UpdateEventUseCase
import com.example.apptareas.ui.common.UiEvent
import com.example.apptareas.utilities.Constantes
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EventDetailsViewModel @Inject constructor(
    private val updateEventUseCase : UpdateEventUseCase,
    private val getEventUseCase: GetEventUseCase,
    @IoDispatcher val dispatcher : CoroutineDispatcher,
) : ViewModel() {
    private val _uiState = MutableStateFlow(EventDetailsState())
    val uiState = _uiState.asStateFlow()

    fun handleEvent (event : EventDetailsEvents) {
        when (event) {
            is EventDetailsEvents.GetEvent -> getEvent(event.eventId)
            is EventDetailsEvents.UpdateEvent -> updateEvent(event.event)
            is EventDetailsEvents.EventDone -> _uiState.update { it.copy(appEvent = null) }
        }
    }

    private fun updateEvent(event: Event) {
        viewModelScope.launch(dispatcher) {
            when (updateEventUseCase.invoke(event)) {
                is NetworkResult.Success -> _uiState.update{ it.copy(appEvent = UiEvent.ShowSnackbar(Constantes.EVENT_UPDATED)) }
                is NetworkResult.Loading -> TODO()
                is NetworkResult.Error -> _uiState.update{ it.copy(appEvent = UiEvent.ShowSnackbar(Constantes.ERROR_UPDATING_EVENT)) }
            }
        }
    }

    private fun getEvent(eventId: Int) {
        viewModelScope.launch(dispatcher) {
            when (val result = getEventUseCase.invoke(eventId)) {
                is NetworkResult.Success -> _uiState.update{ it.copy(event = result.data) }
                is NetworkResult.Loading -> TODO()
                is NetworkResult.Error -> _uiState.update{ it.copy(appEvent = UiEvent.ShowSnackbar(Constantes.ERROR__GETTING_EVENT)) }
            }
        }
    }
}