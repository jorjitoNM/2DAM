package com.example.primeraapp.ui.pantallaAjustes

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.primeraapp.data.remote.NetworkResult
import com.example.primeraapp.di.IoDispatcher
import com.example.primeraapp.domain.modelo.User
import com.example.primeraapp.domain.usecases.userRemote.GetUserUseCase
import com.example.primeraapp.domain.usecases.userRemote.UpdateUserUseCase
import com.example.primeraapp.ui.common.UiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AjustesViewModel @Inject constructor(
    private val updateUserUseCase: UpdateUserUseCase,
    private val getUserUseCase: GetUserUseCase,
    @IoDispatcher val dispatcher: CoroutineDispatcher
) : ViewModel() {

    private val _uiState = MutableStateFlow(AjustesState())
    val uiState = _uiState.asStateFlow()

    fun handleEvent(event: AjustesEvent) {
        when (event) {
            is AjustesEvent.GetUser -> getUser(event.id)
            is AjustesEvent.UpdateUser -> updateUser(event.user)
            AjustesEvent.ErrorMostrado -> errorMostrado()
        }
    }

    private fun updateUser(user: User) {
        viewModelScope.launch(dispatcher) {
            updateUserUseCase.invoke(user.id, user).collect { result ->
                when (result) {
                    is NetworkResult.Error -> {
                        _uiState.update {
                            it.copy(
                                event = UiEvent.ShowSnackbar(result.message),
                                isLoading = false
                            )
                        }
                    }

                    is NetworkResult.Success -> {
                        _uiState.update {
                            it.copy(event = UiEvent.PopBackStack, isLoading = false)
                        }
                    }

                    is NetworkResult.Loading -> {
                        _uiState.update { it.copy(isLoading = true) }
                    }
                }
            }
        }
    }

    private fun getUser(id: Int) {
        viewModelScope.launch(dispatcher) {
            getUserUseCase.invoke(id).collect { result ->
                when (result) {
                    is NetworkResult.Error -> {
                        _uiState.update {
                            it.copy(
                                event = UiEvent.ShowSnackbar(result.message),
                                isLoading = false
                            )
                        }
                    }

                    is NetworkResult.Success -> {
                        _uiState.update { it.copy(user = result.data, isLoading = false) }
                    }

                    is NetworkResult.Loading -> {
                        _uiState.update { it.copy(isLoading = true) }
                    }
                }
            }
        }
    }

    private fun errorMostrado() {
        _uiState.update { it.copy(event = null) }
    }
}
