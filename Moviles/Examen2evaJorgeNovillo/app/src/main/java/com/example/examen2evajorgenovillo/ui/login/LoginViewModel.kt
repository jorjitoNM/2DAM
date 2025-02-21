package com.example.examen2evajorgenovillo.ui.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.examen2evajorgenovillo.R
import com.example.examen2evajorgenovillo.common.NetworkResult
import com.example.examen2evajorgenovillo.common.StringProvider
import com.example.examen2evajorgenovillo.di.IoDispatcher
import com.example.examen2evajorgenovillo.domain.model.User
import com.example.examen2evajorgenovillo.domain.usecases.user.LoginUseCase
import com.example.examen2evajorgenovillo.domain.usecases.user.SaveTokenUseCase
import com.example.examen2evajorgenovillo.ui.common.UiEvent
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
class LoginViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase,
    @IoDispatcher private val dispatcher: CoroutineDispatcher,
    private val stringProvider: StringProvider,
    private val saveTokenUseCase: SaveTokenUseCase,
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

            is LoginEvents.Login -> login(event.user)
            is LoginEvents.EventDone -> _uiState.update { it.copy(event = null) }
        }
    }

    private fun login(user : User) {
        viewModelScope.launch(dispatcher) {
            when (val result = loginUseCase.invoke(user)) {
                is NetworkResult.Success -> {
                    try {
                        saveTokenUseCase.invoke(result.data)
                    } catch (e : Exception) {
                        Timber.e(e.message,e)
                        _uiState.update {
                            it.copy(
                                event = UiEvent.ShowSnackbar(stringProvider.getString(R.string.token_not_saved)),
                                isLoading = false,
                            )
                        }
                    }
                    _uiState.update { it.copy(validated = true, isLoading = false) }
                }
                is NetworkResult.Error -> _uiState.update {
                    it.copy(
                        event = UiEvent.ShowSnackbar(result.message),
                        isLoading = false,
                    )
                }

                is NetworkResult.NotLogged ->  _uiState.update {
                    it.copy(
                        event = UiEvent.ShowSnackbar(stringProvider.getString(R.string.login_error)),
                        isLoading = false,
                    )
                }
                is NetworkResult.Loading -> _uiState.update {
                    it.copy(
                        isLoading = true,
                    )
                }
            }
        }
    }
}