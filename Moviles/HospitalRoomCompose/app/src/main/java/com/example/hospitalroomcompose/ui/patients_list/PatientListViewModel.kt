package com.example.hospitalroomcompose.ui.patients_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.apptareascompose.ui.patients_list.PatientListEvents
import com.example.apptareascompose.ui.patients_list.PatientListState
import com.example.hospitalroomcompose.R
import com.example.hospitalroomcompose.common.StringProvider
import com.example.hospitalroomcompose.domain.usecases.patient.GetAllPatientsUseCase
import com.example.hospitalroomcompose.di.IoDispatcher
import com.example.primeraapp.ui.common.UiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class PatientListViewModel @Inject constructor(
    private val getAllPatientsUseCase: GetAllPatientsUseCase,
    @IoDispatcher val dispatcher: CoroutineDispatcher,
    private val stringProvider: StringProvider,
) : ViewModel() {

    private val _uiState: MutableStateFlow<PatientListState> = MutableStateFlow(PatientListState())
    val uiState: StateFlow<PatientListState> = _uiState.asStateFlow()

    fun handleEvent(event: PatientListEvents) {
        when (event) {
            is PatientListEvents.GetAllPatients -> getAllPatients()
            is PatientListEvents.EventDone -> _uiState.update { it.copy(uiEvent = null) }
        }
    }

    private fun getAllPatients() {
        try {
            viewModelScope.launch(dispatcher) {
                _uiState.update { it.copy(patients = getAllPatientsUseCase.invoke()) }
            }
        } catch (e : Exception) {
            Timber.e(e.message)
            _uiState.update { it.copy(uiEvent = UiEvent.ShowSnackbar(e.message ?: stringProvider.getString(
                R.string.unknownError))) }
        }
    }
}