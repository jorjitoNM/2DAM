package com.example.apptareas.ui.user_profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.apptareas.data.remote.NetworkResult
import com.example.apptareas.domain.usecases.GetUserUseCase
import com.example.apptareas.ui.common.UiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileFragmentViewModel @Inject constructor (
    private val getUserUseCase : GetUserUseCase
) :ViewModel() {
    private val _uiState = MutableStateFlow(ProfileFragmentState())
    val uiState = _uiState.asStateFlow()

    fun handleEvent (event : ProfileFragmentEvents) {
        when (event) {
            is ProfileFragmentEvents.GetUser -> getUser(event.userId)
        }
    }

    private fun getUser(userId: Int) {
        viewModelScope.launch {
            when (val user = getUserUseCase.invoke(userId)) {
                is NetworkResult.Success -> _uiState.update { it.copy(user = user.data) }
                is NetworkResult.Error -> _uiState.update{ it.copy(appEvent = UiEvent.ShowSnackbar(user.message)) }
                is NetworkResult.Loading ->  TODO()
            }
        }
    }

}