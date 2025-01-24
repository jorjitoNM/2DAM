package com.example.apptareascompose.ui.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.apptareascompose.domain.model.User
import com.example.apptareascompose.domain.usecases.login.LoginUseCase
import com.example.apptareascompose.domain.usecases.login.RegisterUserUseCase
import com.example.primeraapp.di.IoDispatcher
import com.example.primeraapp.ui.common.UiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor (
    private val loginUseCase: LoginUseCase,
    private val registerUserUseCase: RegisterUserUseCase,
    @IoDispatcher val dispatcher: CoroutineDispatcher,
) : ViewModel() {

    private val _uiState: MutableStateFlow<LoginState> = MutableStateFlow(LoginState())
    val uiState: StateFlow<LoginState> = _uiState.asStateFlow()

    private val _uiError = Channel<UiEvent>()
    val uiError = _uiError.receiveAsFlow()

    fun handleEvent (event : LoginEvents) {
        when (event) {
            is LoginEvents.login -> login(User(0,event.username,event.password))
            is LoginEvents.register -> register(User(0,event.username,event.password))
        }
    }

    private fun login (user : User) {
        viewModelScope.launch(dispatcher) {
            loginUseCase.invoke(user)
        }
    }

    private fun register (user : User) {
        viewModelScope.launch(dispatcher) {
            //registerUserUseCase.invoke(user)
        }
    }
}