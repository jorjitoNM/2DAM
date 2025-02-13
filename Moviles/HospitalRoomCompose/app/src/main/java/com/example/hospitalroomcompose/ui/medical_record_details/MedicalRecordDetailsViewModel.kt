package com.example.hospitalroomcompose.ui.medical_record_details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.apptareascompose.ui.medical_record_details.MedicalRecordDetailsEvents
import com.example.apptareascompose.ui.medical_record_details.MedicalRecordDetailsState
import com.example.hospitalroomcompose.R
import com.example.hospitalroomcompose.common.StringProvider
import com.example.hospitalroomcompose.domain.usecases.medical_records.GetMedicalRecordUseCase
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
class MedicalRecordDetailsViewModel @Inject constructor(
    private val getMedicalRecordUseCase: GetMedicalRecordUseCase,
    @IoDispatcher val dispatcher: CoroutineDispatcher,
    private val stringProvider: StringProvider,
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
        try {
        viewModelScope.launch(dispatcher) {
            _uiState.update { it.copy(medicalRecord = getMedicalRecordUseCase.invoke(recordId)) }
        }
            } catch (e : Exception) {
        Timber.e(e.message)
        _uiState.update { it.copy(uiEvent = UiEvent.ShowSnackbar(e.message ?: stringProvider.getString(
            R.string.unknownError))) }
    }
    }
}