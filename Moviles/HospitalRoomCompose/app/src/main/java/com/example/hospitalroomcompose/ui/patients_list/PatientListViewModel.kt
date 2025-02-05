package com.example.apptareascompose.ui.patients_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.apptareascompose.common.NetworkResult
import com.example.hospitalroomcompose.domain.usecases.patient.GetAllPatientsUseCase
import com.example.primeraapp.di.IoDispatcher
import com.example.primeraapp.ui.common.UiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PatientListViewModel @Inject constructor(
    private val getAllPatientsUseCase: GetAllPatientsUseCase,
    @IoDispatcher val dispatcher: CoroutineDispatcher
) : ViewModel() {

    private val _uiState: MutableStateFlow<PatientListState> by lazy {
        MutableStateFlow(PatientListState())
    }
    val uiState: StateFlow<PatientListState> = _uiState.asStateFlow()

    private val _uiError = Channel<UiEvent>()
    val uiError = _uiError.receiveAsFlow()

    fun handleEvent(event: PatientListEvents) {
        when (event) {
            is PatientListEvents.GetAllPatients -> getAllPatients()
            is PatientListEvents.EventDone -> _uiState.update { it.copy(uiEvent = null) }
        }
    }

    private fun getAllPatients() {
        viewModelScope.launch(dispatcher) {
            getAllPatientsUseCase.invoke().collect { result ->
                when (result) {
                    is NetworkResult.Success -> _uiState.update {
                        it.copy(
                            patients = result.data,
                            isLoading = false
                        )
                    }

                    is NetworkResult.Error -> _uiState.update {
                        it.copy(
                            uiEvent = UiEvent.ShowSnackbar(result.message), isLoading = false
                        )
                    }

                    is NetworkResult.Loading -> _uiState.update { it.copy(isLoading = true) }
                }
            }
        }
    }
}