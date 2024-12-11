package com.example.apptareas.ui.events_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.apptareas.data.remote.NetworkResult
import com.example.apptareas.domain.model.Event
import com.example.apptareas.domain.usecases.DeleteEventUseCase
import com.example.apptareas.domain.usecases.FilterEventsUseCase
import com.example.apptareas.domain.usecases.GetEventsUseCase
import com.example.apptareas.ui.common.UiEvent
import com.example.apptareas.utilities.Constantes
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EventListViewModel @Inject constructor(
    private val getEventsUseCaseUserCase: GetEventsUseCase,
    private val deleteEventUseCase : DeleteEventUseCase,
    private val filterEventsUseCase: FilterEventsUseCase,
) : ViewModel() {

    private val _uiState = MutableStateFlow(EventListState())
    val uiState = _uiState.asStateFlow()

    fun handleEvent(event: EventListEvents) {
        when (event) {
            is EventListEvents.GetEvents ->  getEvents()
            is EventListEvents.DeleteEvent -> deleteEvent(event.event)
            is EventListEvents.EventDone -> _uiState.update { it.copy(appEvent = null) }
            is EventListEvents.FilterEvents -> filterEvents(event.eventName)
        }
    }

    private fun filterEvents(eventName: String) {
        viewModelScope.launch {
            when (val events = filterEventsUseCase(eventName)) {
                is NetworkResult.Success -> _uiState.update { it.copy(events = events.data) }
                is NetworkResult.Error -> _uiState.update { it.copy(appEvent = UiEvent.ShowSnackbar(events.message))}
                is NetworkResult.Loading -> _uiState.update { it.copy(appEvent = UiEvent.ShowSnackbar(Constantes.LOADING)) }
            }
        }
    }

    private fun deleteEvent(event: Event) {
        viewModelScope.launch {
            if (deleteEventUseCase.invoke(event))
                _uiState.update { it.copy(appEvent = UiEvent.ShowSnackbar(Constantes.EVENT_DELETED)) }
            else
                _uiState.update { it.copy(appEvent = UiEvent.ShowSnackbar(Constantes.ERROR_DELETING_EVENT)) }
        }
    }

    private fun getEvents() {
        viewModelScope.launch {
            when (val userEvents = getEventsUseCaseUserCase.invoke()) {
                is NetworkResult.Success -> _uiState.update{ it.copy(events = userEvents.data) }

                is NetworkResult.Error -> _uiState.update{ it.copy(
                    appEvent =
                    UiEvent.ShowSnackbar(userEvents.message)
                ) }

                is NetworkResult.Loading -> TODO()
            }
        }
    }
}