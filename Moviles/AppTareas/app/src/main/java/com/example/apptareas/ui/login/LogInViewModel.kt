package com.example.apptareas.ui.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.apptareas.data.NetworkResult
import com.example.apptareas.domain.model.User
import com.example.apptareas.domain.usecases.user_usercases.LogInUseCase
import com.example.apptareas.ui.common.UiEvent
import com.example.apptareas.utilities.Constantes
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LogInViewModel  @Inject constructor (
    private val logInUseCase: LogInUseCase,
) : ViewModel() {

    private val _uiState = MutableStateFlow(LogInState())
    val uiState = _uiState.asStateFlow()

    fun handleEvent(event: LogInEvents) {
        when (event) {
            is LogInEvents.LogIn -> logIn(event.user)
            is LogInEvents.ShowEvent -> _uiState.update{ it.copy(event = null) }
        }
    }

    private fun logIn(user: User) {
        viewModelScope.launch {
            when (logInUseCase.invoke(user)) {
                is NetworkResult.Success -> _uiState.update{ it.copy(logged = true) }
                is NetworkResult.Error -> _uiState.update{ it.copy(event = UiEvent.ShowSnackbar(Constantes.LOGIN_ERROR)) }
                is NetworkResult.Loading -> TODO()
            }
        }
    }
}