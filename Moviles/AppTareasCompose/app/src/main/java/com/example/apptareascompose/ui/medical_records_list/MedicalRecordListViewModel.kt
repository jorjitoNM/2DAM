package com.example.apptareascompose.ui.medical_records_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.apptareascompose.common.NetworkResult
import com.example.apptareascompose.domain.usecases.medical_records.GetPatientMedicalRecordsUseCase
import com.example.primeraapp.di.IoDispatcher
import com.example.primeraapp.ui.common.UiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MedicalRecordListViewModel @Inject constructor(
    private val getPatientMedicalRecordsUseCase: GetPatientMedicalRecordsUseCase,
    @IoDispatcher val dispatcher: CoroutineDispatcher
) : ViewModel() {
    private val _uiState: MutableStateFlow<MedicalRecordListState> by lazy {
        MutableStateFlow(MedicalRecordListState())
    }
    val uiState: StateFlow<MedicalRecordListState> = _uiState.asStateFlow()

    fun handleEvent(event: MedicalRecordListEvents) {
        when (event) {
            is MedicalRecordListEvents.GetAllMedicalRecord -> getAll(event.patientId)
            is MedicalRecordListEvents.EventDone -> _uiState.update { it.copy(uiEvent = null) }
        }
    }

    private fun getAll(patientId: Int) {
        viewModelScope.launch(dispatcher) {
            getPatientMedicalRecordsUseCase.invoke(patientId).collect { result ->
                when (result) {
                    is NetworkResult.Success -> _uiState.update {
                        it.copy(
                            medicalRecords = result.data,
                            isLoading = false
                        )
                    }

                    is NetworkResult.Error -> _uiState.update {
                        it.copy(
                            isLoading = false,
                            uiEvent = UiEvent.ShowSnackbar(result.message)
                        )
                    }

                    is NetworkResult.Loading -> _uiState.update { it.copy(isLoading = true) }
                }
            }
        }
    }
}