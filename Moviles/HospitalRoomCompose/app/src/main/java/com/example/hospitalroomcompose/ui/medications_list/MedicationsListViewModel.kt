package com.example.hospitalroomcompose.ui.medications_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hospitalroomcompose.domain.usecases.medications.GetAllMedicationsUseCase
import com.example.primeraapp.di.IoDispatcher
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MedicationsListViewModel @Inject constructor (
    private val getAllMedicationsUseCase : GetAllMedicationsUseCase,
    @IoDispatcher val dispatcher: CoroutineDispatcher,
) : ViewModel() {

    private val _uiState: MutableStateFlow<MedicationsListsState> by lazy {
        MutableStateFlow(MedicationsListsState())
    }
    val uiState: StateFlow<MedicationsListsState> = _uiState.asStateFlow()

    fun handleEvent (event : MedicationListEvents) {
        when (event) {
            is MedicationListEvents.GetAllMedications -> getAllMedications()
            is MedicationListEvents.EventDone -> _uiState.update { it.copy(uiEvent = null) }
        }
    }

    private fun getAllMedications () {
        viewModelScope.launch(dispatcher) {
           _uiState.update { it.copy(medications = getAllMedicationsUseCase.invoke()) }
        }
    }
}