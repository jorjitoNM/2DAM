package com.example.apptareascompose.ui.doctors_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.apptareascompose.common.NetworkResult
import com.example.apptareascompose.domain.usecases.doctor.GetAllDoctorsUseCase
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
class DoctorsListViewModel @Inject constructor(
    private val getAllDoctorsUseCase: GetAllDoctorsUseCase,
    @IoDispatcher val dispatcher: CoroutineDispatcher
) : ViewModel() {
    private val _uiState: MutableStateFlow<DoctorsListState> by lazy {
        MutableStateFlow(DoctorsListState())
    }
    val uiState: StateFlow<DoctorsListState> = _uiState.asStateFlow()

    fun handleEvent (event: DoctorListEvents) {
        when (event) {
            is DoctorListEvents.GetAllDoctors -> getAllDoctors()
            is DoctorListEvents.EventDone -> _uiState.update { it.copy(uiEvent = null) }
        }
    }

    private fun getAllDoctors() {
        viewModelScope.launch(dispatcher) {
            getAllDoctorsUseCase.invoke().collect {result ->
                when (result) {
                    is NetworkResult.Success -> _uiState.update {
                        it.copy(
                            doctors = result.data,
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