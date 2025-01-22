package com.example.apptareascompose.ui.login

import androidx.lifecycle.ViewModel
import com.example.apptareascompose.domain.usecases.login.LoginUseCase
import com.example.apptareascompose.domain.usecases.login.RegisterUserUseCase
import com.example.primeraapp.di.IoDispatcher
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor (
    val loginUseCase: LoginUseCase,
    val registerUserUseCase: RegisterUserUseCase,
    @IoDispatcher val dispatcher: CoroutineDispatcher,
) : ViewModel() {

    private val _uiState: MutableStateFlow<LoginState> = MutableStateFlow(LoginState())
    val uiState: StateFlow<LoginState> = _uiState.asStateFlow()
}