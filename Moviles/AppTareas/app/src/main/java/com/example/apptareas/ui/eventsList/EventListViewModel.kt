package com.example.apptareas.ui.eventsList

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.apptareas.domain.usecases.GetEventsUseCase
import com.example.viewmodel.data.remote.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EventListViewModel @Inject constructor (
    private val getEventsUseCaseUserCase: GetEventsUseCase,
) : ViewModel() {

    private val _uiState = MutableLiveData(EventListState())
    val uiState: LiveData<EventListState> get() = _uiState

    fun handleEvent(event: EventListEvents) {
        when (event) {
            is EventListEvents.GetEvents -> getEvent(event.userId)
        }
    }

    private fun getEvent(userId: Int) {
        viewModelScope.launch {
            when (val userEvents = getEventsUseCaseUserCase.invoke(userId)) {
                is NetworkResult.Success -> {
                    _uiState.value = _uiState.value?.copy(logged = true)
                }
                is NetworkResult.Error -> _uiState.value = _uiState.value?.copy(logged = true)
                is NetworkResult.Loading -> TODO()
            }
        }
    }
}