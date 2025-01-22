package com.example.primeraapp.ui.pantallaComments

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.primeraapp.data.remote.NetworkResult
import com.example.primeraapp.di.IoDispatcher
import com.example.primeraapp.domain.modelo.Comment
import com.example.primeraapp.domain.usecases.comments.GetCommentsUseCase
import com.example.primeraapp.ui.common.UiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CommentViewModel @Inject constructor(
    private val getCommentsUseCase: GetCommentsUseCase,
    @IoDispatcher val dispatcher: CoroutineDispatcher
) : ViewModel() {

    private val _uiState = MutableStateFlow(CommentState())
    val uiState = _uiState.asStateFlow()

    private var lastDeletedPost: Comment? = null

    fun handleEvent(event: CommentEvent) {
        when (event) {
            is CommentEvent.GetComments -> getComments(event.id)
            CommentEvent.ErrorMostrado -> errorMostrado()
            CommentEvent.UndoDelete -> undoDelete()
        }
    }

    private fun getComments(id: Int) {
        viewModelScope.launch(dispatcher) {
            getCommentsUseCase.invoke(id).collect { result ->
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
                        val comments = result.data
                        _uiState.update {
                            it.copy(comments = comments, isLoading = false)
                        }
                    }

                    is NetworkResult.Loading -> {
                        _uiState.update { it.copy(isLoading = true) }
                    }
                }
            }
        }
    }

    private fun undoDelete() {
        lastDeletedPost?.let { comment ->
            val updatedPosts = _uiState.value.comments.toMutableList().apply {
                add(0, comment)
            }

            _uiState.update {
                it.copy(comments = updatedPosts, event = null)
            }

            lastDeletedPost = null
        }
    }

    private fun errorMostrado() {
        _uiState.update { it.copy(event = null) }
    }
}
