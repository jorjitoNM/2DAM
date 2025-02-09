package com.example.primeraapp.ui.pantallaPosts

import com.example.primeraapp.domain.modelo.Post
import com.example.primeraapp.ui.common.UiEvent

data class PostState(
    val posts: List<Post> = emptyList(),
    val event: UiEvent? = null,
    val isLoading: Boolean = false
)