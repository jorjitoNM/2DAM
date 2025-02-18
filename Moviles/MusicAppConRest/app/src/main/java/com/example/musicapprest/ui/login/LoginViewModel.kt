package com.example.musicapprest.ui.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.musicapprest.R
import com.example.musicapprest.common.NetworkResult
import com.example.musicapprest.common.StringProvider
import com.example.musicapprest.di.IoDispatcher
import com.example.musicapprest.domain.model.User
import com.example.musicapprest.domain.usecases.user.LoginUseCase
import com.example.musicapprest.domain.usecases.user.RegisterUserUseCase
import com.example.musicapprest.domain.usecases.user.SaveUserNameUseCase
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
class LoginViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase,
    private val registerUserUseCase: RegisterUserUseCase,
    private val saveTokenUseCase: SaveUserNameUseCase,
    @IoDispatcher private val dispatcher: CoroutineDispatcher,
    private val stringProvider: StringProvider,
) : ViewModel() {

    private val _uiState: MutableStateFlow<LoginState> = MutableStateFlow(LoginState())
    val uiState: StateFlow<LoginState> = _uiState.asStateFlow()

    fun handleEvent(event: LoginEvents) {
        when (event) {
            is LoginEvents.UpdateUsername -> {
                _uiState.update { currentState ->
                    currentState.copy(
                        user = currentState.user.copy(email = event.username)
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

            is LoginEvents.Login -> login(event.user)
            is LoginEvents.Register -> register(event.user)
            is LoginEvents.EventDone -> _uiState.update { it.copy(event = null) }
        }
    }

    private fun register(user : User) {
        viewModelScope.launch(dispatcher) {
            when (val result = registerUserUseCase.invoke(user)) {
                is NetworkResult.Success -> _uiState.value =
                    _uiState.value.copy(event = UiEvent.ShowSnackbar(stringProvider.getString(R.string.user_registered)))

                is NetworkResult.Error -> _uiState.update {
                    it.copy(
                        event = UiEvent.ShowSnackbar(result.message),
                        isLoading = false,
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

    private fun login(user : User) {
        viewModelScope.launch(dispatcher) {
            when (val result = loginUseCase.invoke(user)) {
                is NetworkResult.Success -> saveTokenUseCase.invoke(result.data)
                is NetworkResult.Error -> _uiState.update {
                    it.copy(
                        event = UiEvent.ShowSnackbar(result.message),
                        isLoading = false,
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