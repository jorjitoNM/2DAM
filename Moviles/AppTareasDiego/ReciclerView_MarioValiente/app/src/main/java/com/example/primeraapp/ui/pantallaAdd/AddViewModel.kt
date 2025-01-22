package com.example.primeraapp.ui.pantallaAdd

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.primeraapp.data.remote.NetworkResult
import com.example.primeraapp.di.IoDispatcher
import com.example.primeraapp.domain.modelo.Post
import com.example.primeraapp.domain.usecases.posts.AddPostUseCase
import com.example.primeraapp.ui.common.UiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddViewModel @Inject constructor(
    private val addPostUseCase: AddPostUseCase,
    @IoDispatcher val dispatcher: CoroutineDispatcher
) : ViewModel() {

    private val _uiState = MutableStateFlow(AddState())
    val uiState = _uiState.asStateFlow()

    fun handleEvent(event: AddEvent) {
        when (event) {
            is AddEvent.AddPost -> addPersona(event.post)
            AddEvent.ErrorMostrado -> errorMostrado()
        }
    }

    private fun addPersona(post: Post) {
        viewModelScope.launch(dispatcher) {
            addPostUseCase.invoke(post).collect { result ->
                when (result) {
                    is NetworkResult.Error -> {
                        _uiState.update {
                            it.copy(
                                event = UiEvent.ShowSnackbar(result.message),
                                isLoading = false
                            )
                        }
                    }

                    is NetworkResult.Success -> {
                        _uiState.update {
                            it.copy(event = UiEvent.PopBackStack, isLoading = false)
                        }
                    }

                    is NetworkResult.Loading -> {
                        _uiState.update { it.copy(isLoading = true) }
                    }
                }
            }
        }
    }

    private fun errorMostrado() {
        _uiState.update { it.copy(event = null) }
    }
}
