package com.example.apptareas.ui.user_profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.apptareas.R
import com.example.apptareas.data.remote.NetworkResult
import com.example.apptareas.domain.usecases.GetUserUseCase
import com.example.apptareas.ui.common.UiEvent
import com.example.apptareas.utilities.Constantes
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class ProfileFragmentViewModel @Inject constructor (
    private val getUserUseCase : GetUserUseCase
) :ViewModel() {
    private val _uiState = MutableLiveData(ProfileFragmentState())
    val uiState: LiveData<ProfileFragmentState> get() = _uiState

    fun handleEvent (event : ProfileFragmentEvents) {
        when (event) {
            is ProfileFragmentEvents.GetUser -> getUser(event.userId)
        }
    }

    private fun getUser(userId: Int) {
        viewModelScope.launch {
            when (val user = getUserUseCase.invoke(userId)) {
                is NetworkResult.Success -> _uiState.value = _uiState.value?.copy(user = user.data)
                is NetworkResult.Error -> _uiState.value = _uiState.value?.copy(appEvent = UiEvent.ShowSnackbar(user.message))
                is NetworkResult.Loading ->  {
                    Timber.i(Constantes.PETITION_RETURNED_LOADING)
                    getUser(userId)
                }
            }
        }
    }

}