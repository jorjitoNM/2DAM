package com.example.primeraapp.ui.loginActivity

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.primeraapp.domain.usecases.userLocal.UserLocalLogin
import com.example.primeraapp.domain.usecases.userLocal.UserLocalRegister
import com.example.primeraapp.ui.common.ConstantesUI
import com.example.primeraapp.ui.common.UiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val userRegister: UserLocalRegister,
    private val userLogin: UserLocalLogin
) : ViewModel() {

    private val _uiState = MutableStateFlow(LoginState())
    val uiState = _uiState.asStateFlow()

    fun handleEvent(event: LoginEvent) {
        when (event) {
            is LoginEvent.registerUser -> registerUser(event.username, event.password)
            is LoginEvent.validateUser -> validateUser(event.username, event.password)
        }
    }

    private fun registerUser(username: String, password: String) {
        viewModelScope.launch {
            try {

                userRegister.invoke(username, password)
                _uiState.value =
                    _uiState.value.copy(event = UiEvent.ShowSnackbar(ConstantesUI.USER_REGISTER_SUCCESS))
            } catch (e: Exception) {
                _uiState.value =
                    _uiState.value.copy(event = UiEvent.ShowSnackbar(ConstantesUI.USER_REGISTER_ERROR))
            }
        }
    }

    private fun validateUser(username: String, password: String) {
        viewModelScope.launch {
            userLogin.invoke(username, password)
                .collect { result ->
                    result.fold(
                        onSuccess = { user ->
                            if (user != null) {
                                _uiState.update {
                                    it.copy(validado = true, userId = user.id)
                                }
                            } else {
                                _uiState.update {
                                    it.copy(event = UiEvent.ShowSnackbar(ConstantesUI.CREDENCIALES_INCORRECTAS))
                                }
                            }
                        },
                        onFailure = { exception ->
                            _uiState.update {
                                it.copy(event = UiEvent.ShowSnackbar("${ConstantesUI.ERROR_VALIDACION} ${exception.message}"))
                            }
                        }
                    )
                }
        }
    }


}
