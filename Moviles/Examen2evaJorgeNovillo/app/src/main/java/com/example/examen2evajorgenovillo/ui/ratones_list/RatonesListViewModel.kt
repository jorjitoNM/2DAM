package com.example.examen2evajorgenovillo.ui.ratones_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.examen2evajorgenovillo.common.NetworkResult
import com.example.examen2evajorgenovillo.di.IoDispatcher
import com.example.examen2evajorgenovillo.domain.usecases.ratones.GetAllRatonesUseCase
import com.example.examen2evajorgenovillo.ui.common.UiEvent
import com.example.examen2evajorgenovillo.ui.login.LoginState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RatonesListViewModel @Inject constructor(
    private val getAllRatonesUseCase: GetAllRatonesUseCase,
    @IoDispatcher private val dispatcher: CoroutineDispatcher
) : ViewModel() {

    private val _uiState: MutableStateFlow<RatonesListState> = MutableStateFlow(RatonesListState())
    val uiState: StateFlow<RatonesListState> = _uiState.asStateFlow()

    fun handleEvent (event : RatonesListEvents) {
        when (event) {
            is RatonesListEvents.EventDone -> _uiState.update { it.copy(event = null) }
            is RatonesListEvents.GetAll -> getAll()
        }
    }

    private fun getAll () {
        viewModelScope.launch(dispatcher) {
            when (val result = getAllRatonesUseCase.invoke()) {
                is NetworkResult.Success -> _uiState.update {
                    it.copy(
                        ratones = result.data,
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