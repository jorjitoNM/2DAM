package com.example.primeraapp.ui.pantallaPostsOfUser

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.primeraapp.data.remote.NetworkResult
import com.example.primeraapp.di.IoDispatcher
import com.example.primeraapp.domain.modelo.Post
import com.example.primeraapp.domain.usecases.posts.DeletePostUserCase
import com.example.primeraapp.domain.usecases.posts.GetPostsOfUserUseCase
import com.example.primeraapp.ui.common.ConstantesUI
import com.example.primeraapp.ui.common.UiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PostUserViewModel @Inject constructor(
    private val getPostsOfUserUseCase: GetPostsOfUserUseCase,
    private val deletePostUserCase: DeletePostUserCase,
    @IoDispatcher val dispatcher: CoroutineDispatcher
) : ViewModel() {

    private val _uiState = MutableStateFlow(PostUserState())
    val uiState = _uiState.asStateFlow()

    private var lastDeletedPost: Post? = null

    fun handleEvent(event: PostUserEvent) {
        when (event) {
            is PostUserEvent.GetPostsOfUser -> getPosts(event.userId)
            is PostUserEvent.DeletePersona -> deletePost(event.postId)
            PostUserEvent.ErrorMostrado -> errorMostrado()
            PostUserEvent.UndoDelete -> undoDelete()
        }
    }

    private fun deletePost(postId: Int) {
        viewModelScope.launch(dispatcher) {
            deletePostUserCase.invoke(postId).collect { result ->
                when (result) {
                    is NetworkResult.Loading -> {
                        _uiState.update { it.copy(isLoading = true) }
                    }

                    is NetworkResult.Error -> {
                        _uiState.update {
                            it.copy(event = UiEvent.ShowSnackbar(result.message), isLoading = false)
                        }
                    }

                    is NetworkResult.Success -> {
                        _uiState.update {
                            it.copy(
                                event = UiEvent.ShowSnackbar(ConstantesUI.DELETE),
                                isLoading = false
                            )
                        }
                    }
                }
            }
        }
    }

    private fun getPosts(userId: Int) {
        viewModelScope.launch(dispatcher) {
            getPostsOfUserUseCase.invoke(userId).collect { result ->
                when (result) {
                    is NetworkResult.Loading -> {
                        _uiState.update { it.copy(isLoading = true) }
                    }

                    is NetworkResult.Error -> {
                        _uiState.update {
                            it.copy(event = UiEvent.ShowSnackbar(result.message), isLoading = false)
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
