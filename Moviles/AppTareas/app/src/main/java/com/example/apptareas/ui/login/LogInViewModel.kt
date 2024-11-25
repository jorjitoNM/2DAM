package com.example.apptareas.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.apptareas.R
import com.example.apptareas.domain.model.User
import com.example.apptareas.domain.usecases.LogInUseCase
import com.example.apptareas.ui.common.UiEvent
import com.example.apptareas.data.remote.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LogInViewModel  @Inject constructor (
    private val logInUseCase: LogInUseCase,
) : ViewModel() {

    private val _uiState = MutableLiveData(LogInState())
    val uiState: LiveData<LogInState> get() = _uiState

    fun handleEvent(event: LogInEvents) {
        when (event) {
            is LogInEvents.LogIn -> logIn(event.user)
            is LogInEvents.ShowEvent -> _uiState.value = _uiState.value?.copy(event = null)
        }
    }

    private fun logIn(user: User) {
        viewModelScope.launch {
            when (val userMatch = logInUseCase.invoke(user)) {
                is NetworkResult.Success -> {
                    _uiState.value = _uiState.value?.copy(user = userMatch.data, logged = true)
                }
                is NetworkResult.Error -> _uiState.value = _uiState.value?.copy(event =
                UiEvent.ShowSnackbar(R.string.login_error.toString()))
                is NetworkResult.Loading -> TODO()
            }
        }
    }
}