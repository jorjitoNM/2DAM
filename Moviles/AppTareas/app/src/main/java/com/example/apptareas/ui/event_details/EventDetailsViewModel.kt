package com.example.apptareas.ui.event_details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.apptareas.domain.model.Event
import com.example.apptareas.domain.usecases.UpdateEventUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EventDetailsViewModel @Inject constructor(
    val updateEventUseCase : UpdateEventUseCase,
) : ViewModel() {
    private val _uiState = MutableLiveData(EventDetailsState())
    val uiState: LiveData<EventDetailsState> get() = _uiState

    fun handleEvent (event : EventDetailsEvents) {
        when (event) {
            is EventDetailsEvents.UpdateEvent -> updateEvent(event.userEvent)
            is EventDetailsEvents.EventDone -> _uiState.value = _uiState.value?.copy(event = null)
        }
    }

    private fun updateEvent(userEvent: Event) {
        viewModelScope.launch {
            when (updateEventUseCase)
        }
    }

}