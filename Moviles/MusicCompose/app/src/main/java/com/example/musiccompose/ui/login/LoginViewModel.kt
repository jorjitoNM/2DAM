package com.example.musiccompose.ui.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.musiccompose.di.IoDispatcher
import com.example.musiccompose.domain.usecases.user.LoginUseCase
import com.example.musiccompose.domain.usecases.user.RegisterUserUseCase
import com.example.musiccompose.domain.usecases.user.SaveUserNameUseCase
import com.example.musiccompose.ui.common.Constantes
import com.example.musiccompose.ui.common.UiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase,
    private val registerUserUseCase: RegisterUserUseCase,
    private val saveUserNameUseCase: SaveUserNameUseCase,
    @IoDispatcher private val dispatcher: CoroutineDispatcher,
) : ViewModel() {

    private val _uiState: MutableStateFlow<LoginState> = MutableStateFlow(LoginState())
    val uiState: StateFlow<LoginState> = _uiState.asStateFlow()

    fun handleEvent(event: LoginEvents) {
        when (event) {
            is LoginEvents.UpdateUsername -> {
                _uiState.update { currentState ->
                    currentState.copy(
                        user = currentState.user.copy(username = event.username)
                    )
                }
            }
            is LoginEvents.UpdatePassword -> {
                _uiState.update { currentState ->
                    currentState.copy(
                        user = currentState.user.copy(password = event.password)
                    )
                }
            }
            is LoginEvents.Login -> login(event.user.username, event.user.password)
            is LoginEvents.Register -> register(event.user.username, event.user.password)
            is LoginEvents.EventDone -> _uiState.update { it.copy(uiEvent = null) }
        }
    }

    private fun register(username: String, password: String) {
        viewModelScope.launch(dispatcher) {
            try {
                registerUserUseCase.invoke(username, password)
                _uiState.value =
                    _uiState.value.copy(uiEvent = UiEvent.ShowSnackbar(Constantes.USER_REGISTER_SUCCESS))
            } catch (e: Exception) {
                _uiState.value =
                    _uiState.value.copy(uiEvent = UiEvent.ShowSnackbar(Constantes.USER_REGISTER_ERROR))
            }
        }
    }

    private fun login(username: String, password: String) {
        viewModelScope.launch(dispatcher) {
            loginUseCase.invoke(username, password).collect { result ->
                result.fold(
                    onSuccess = { user ->
                        if (user != null) {
                            _uiState.update {
                                it.copy(validated = true)
                            }
                            saveUserNameUseCase.invoke(username)
                        } else {
                            _uiState.update {
                                it.copy(uiEvent = UiEvent.ShowSnackbar(Constantes.CREDENCIALES_INCORRECTAS))
                            }
                        }
                    },
                    onFailure = { exception ->
                        _uiState.update {
                            it.copy(uiEvent = UiEvent.ShowSnackbar("${Constantes.ERROR_VALIDACION} ${exception.message}"))
                        }
                    }
                )
            }
        }
    }
}