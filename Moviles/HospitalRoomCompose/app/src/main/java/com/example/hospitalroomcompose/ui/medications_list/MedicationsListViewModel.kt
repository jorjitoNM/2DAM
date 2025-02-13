package com.example.hospitalroomcompose.ui.medications_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hospitalroomcompose.R
import com.example.hospitalroomcompose.common.StringProvider
import com.example.hospitalroomcompose.domain.usecases.medications.GetAllMedicationsUseCase
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
class MedicationsListViewModel @Inject constructor(
    private val getAllMedicationsUseCase: GetAllMedicationsUseCase,
    @IoDispatcher val dispatcher: CoroutineDispatcher,
    private val stringProvider: StringProvider,
) : ViewModel() {

    private val _uiState: MutableStateFlow<MedicationsListsState> by lazy {
        MutableStateFlow(MedicationsListsState())
    }
    val uiState: StateFlow<MedicationsListsState> = _uiState.asStateFlow()

    fun handleEvent(event: MedicationListEvents) {
        when (event) {
            is MedicationListEvents.GetAllMedications -> getAllMedications()
            is MedicationListEvents.EventDone -> _uiState.update { it.copy(uiEvent = null) }
        }
    }

    private fun getAllMedications() {
        try {
            viewModelScope.launch(dispatcher) {
                _uiState.update { it.copy(medications = getAllMedicationsUseCase.invoke()) }
            }
        } catch (e: Exception) {
            Timber.e(e.message)
            _uiState.update {
                it.copy(
                    uiEvent = UiEvent.ShowSnackbar(
                        e.message ?: stringProvider.getString(R.string.unknownError)
                    )
                )
            }
        }
    }
}