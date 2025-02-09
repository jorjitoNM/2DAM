package com.example.apptareascompose.ui.medical_record_details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.apptareascompose.common.NetworkResult
import com.example.apptareascompose.domain.usecases.medical_records.GetMedicalRecordUseCase
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
class MedicalRecordDetailsViewModel @Inject constructor(
    private val getMedicalRecordUseCase: GetMedicalRecordUseCase,
    @IoDispatcher val dispatcher: CoroutineDispatcher
) : ViewModel() {

    private val _uiState: MutableStateFlow<MedicalRecordDetailsState> by lazy {
        MutableStateFlow(MedicalRecordDetailsState())
    }
    val uiState: StateFlow<MedicalRecordDetailsState> = _uiState.asStateFlow()

    fun handleEvent (event : MedicalRecordDetailsEvents) {
        when (event) {
            is MedicalRecordDetailsEvents.GetMedicalRecords -> getMedicalRecord(event.recordId)
            is MedicalRecordDetailsEvents.EventDone -> _uiState.update { it.copy(uiEvent = null) }
        }
    }

    private fun getMedicalRecord(recordId: Int) {
        viewModelScope.launch(dispatcher) {
            getMedicalRecordUseCase.invoke(recordId).collect { result ->
                when (result) {
                    is NetworkResult.Success -> _uiState.update {
                        it.copy(
                            medicalRecord = result.data,
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