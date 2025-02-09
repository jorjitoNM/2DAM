package com.example.hospitalroomcompose.ui.medical_records_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.apptareascompose.ui.medical_records_list.MedicalRecordListEvents
import com.example.apptareascompose.ui.medical_records_list.MedicalRecordListState
import com.example.hospitalroomcompose.data.DataStoreRepository
import com.example.hospitalroomcompose.domain.usecases.medical_records.GetPatientMedicalRecordsUseCase
import com.example.primeraapp.di.IoDispatcher
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MedicalRecordListViewModel @Inject constructor(
    private val getPatientMedicalRecordsUseCase: GetPatientMedicalRecordsUseCase,
    private val dataStoreRepository: DataStoreRepository,
    @IoDispatcher private val dispatcher: CoroutineDispatcher
) : ViewModel() {
    private val _uiState: MutableStateFlow<MedicalRecordListState> by lazy {
        MutableStateFlow(MedicalRecordListState())
    }
    val uiState: StateFlow<MedicalRecordListState> = _uiState.asStateFlow()

    val userName: StateFlow<String> = dataStoreRepository.userName
        .stateIn(
            scope = CoroutineScope(dispatcher),
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = "Yotoko Tutoto"
        )

    fun handleEvent(event: MedicalRecordListEvents) {
        when (event) {
            is MedicalRecordListEvents.GetAllMedicalRecord -> getAll(event.patientId)
            is MedicalRecordListEvents.EventDone -> _uiState.update {
                it.copy(uiEvent = null)
            }
        }
    }

    private fun getAll(patientId: Int) {
        viewModelScope.launch(dispatcher) {
            _uiState.update {
                it.copy(
                    medicalRecords = getPatientMedicalRecordsUseCase.invoke(
                        patientId
                    )
                )
            }
        }
    }
}