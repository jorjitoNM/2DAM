package com.example.primeraapp.ui.pantallaPosts

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.primeraapp.data.remote.NetworkResult
import com.example.primeraapp.di.IoDispatcher
import com.example.primeraapp.domain.modelo.Post
import com.example.primeraapp.domain.usecases.posts.GetPostsUseCase
import com.example.primeraapp.ui.common.UiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PostViewModel @Inject constructor(
    private val getPostsUseCase: GetPostsUseCase,
    @IoDispatcher val dispatcher: CoroutineDispatcher
) : ViewModel() {

    private val _uiState = MutableStateFlow(PostState())
    val uiState = _uiState.asStateFlow()

    private var lastDeletedPost: Post? = null

    fun handleEvent(event: PostEvent) {
        when (event) {
            is PostEvent.GetPost -> getPosts(event.id)
            PostEvent.ErrorMostrado -> errorMostrado()
            PostEvent.UndoDelete -> undoDelete()
        }
    }

    private fun getPosts(id: Int) {
        viewModelScope.launch(dispatcher) {
            getPostsUseCase.invoke(id).collect { result ->
                when (result) {
                    is NetworkResult.Loading -> {
                        _uiState.update { it.copy(isLoading = true) }
                    }

                    is NetworkResult.Error -> {
                        _uiState.update {
                            it.copy(
                                event = UiEvent.ShowSnackbar(result.message),
                                isLoading = false
                            )
                        }
                    }

                    is NetworkResult.Success -> {
                        val posts = result.data
                        _uiState.update {
                            it.copy(posts = posts, isLoading = false)
                        }
                    }
                }
            }
        }
    }

    private fun undoDelete() {
        lastDeletedPost?.let { post ->
            val updatedPosts = _uiState.value.posts.toMutableList().apply {
                add(0, post)
            }
            _uiState.update {
                it.copy(posts = updatedPosts, event = null)
            }
            lastDeletedPost = null
        }
    }

    private fun errorMostrado() {
        _uiState.update { it.copy(event = null) }
    }
}


