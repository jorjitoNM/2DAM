package com.example.apptareas.ui.events_list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.apptareas.R
import com.example.apptareas.domain.usecases.GetEventsUseCase
import com.example.apptareas.ui.common.UiEvent
import com.example.apptareas.data.remote.NetworkResult
import com.example.apptareas.domain.model.Event
import com.example.apptareas.domain.usecases.DeleteEventUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EventListViewModel @Inject constructor(
    private val getEventsUseCaseUserCase: GetEventsUseCase,
    private val deleteEventUseCase : DeleteEventUseCase,
) : ViewModel() {

    private val _uiState = MutableLiveData(EventListState())
    val uiState: LiveData<EventListState> get() = _uiState

    fun handleEvent(event: EventListEvents) {
        when (event) {
            is EventListEvents.GetEvents ->  getEvent(event.userId)
            is EventListEvents.DeleteEvent -> deleteEvent(event.event)
            is EventListEvents.EventDone -> _uiState.value = _uiState.value?.copy(appEvent = null)
        }
    }

    private fun deleteEvent(event: Event) {
        viewModelScope.launch {
            if (deleteEventUseCase.invoke(event))
                _uiState.value = _uiState.value?.copy(appEvent = UiEvent.ShowSnackbar(R.string.event_deleted.toString()))
            else
                _uiState.value = _uiState.value?.copy(appEvent = UiEvent.ShowSnackbar(R.string.error_deleting_event.toString()))
        }
    }

    private fun getEvent(userId: Int) {
        viewModelScope.launch {
            when (val userEvents = getEventsUseCaseUserCase.invoke(userId)) {
                is NetworkResult.Success -> {
                    _uiState.value = _uiState.value?.copy(events = userEvents.data)
                }

                is NetworkResult.Error -> _uiState.value = _uiState.value?.copy(
                    appEvent =
                    UiEvent.ShowSnackbar(userEvents.message)
                )

                is NetworkResult.Loading -> TODO()
            }
        }
    }
}