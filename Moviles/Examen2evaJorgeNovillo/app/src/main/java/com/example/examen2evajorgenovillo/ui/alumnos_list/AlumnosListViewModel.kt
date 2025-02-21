package com.example.examen2evajorgenovillo.ui.alumnos_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.examen2evajorgenovillo.common.NetworkResult
import com.example.examen2evajorgenovillo.di.IoDispatcher
import com.example.examen2evajorgenovillo.domain.usecases.alumnos.GetAllAlumnosUseCase
import com.example.examen2evajorgenovillo.ui.common.UiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AlumnosListViewModel @Inject constructor(
    private val getAllAlumnosUseCase: GetAllAlumnosUseCase,
    @IoDispatcher private val dispatcher: CoroutineDispatcher,
) : ViewModel() {

    private val _uiState: MutableStateFlow<AlumnosListState> = MutableStateFlow(AlumnosListState())
    val uiState: StateFlow<AlumnosListState> = _uiState.asStateFlow()

    fun handleEvent (event : AlumnosListEvents) {
        when (event) {
            is AlumnosListEvents.GetAll -> getAll()
            is AlumnosListEvents.EventDone -> _uiState.update {
                it.copy(
                    event = null
                )
            }
        }
    }

    private fun getAll() {
        viewModelScope.launch(dispatcher) {
            when (val result = getAllAlumnosUseCase.invoke()) {
                is NetworkResult.Success -> _uiState.update {
                    it.copy(
                        alumnos = result.data,
                        isLoading = false
                    )
                }
                is NetworkResult.Error -> _uiState.update {
                    it.copy(
                        event = UiEvent.ShowSnackbar(result.message),
                        isLoading = false
                    )
                }
                is NetworkResult.NotLogged -> _uiState.update {
                    it.copy(
                        event = UiEvent.NavigateToLogin,
                        isLoading = false
                    )
                }
                is NetworkResult.Loading -> _uiState.update {
                    it.copy(
                        isLoading = true
                    )
                }
            }
        }
    }
}