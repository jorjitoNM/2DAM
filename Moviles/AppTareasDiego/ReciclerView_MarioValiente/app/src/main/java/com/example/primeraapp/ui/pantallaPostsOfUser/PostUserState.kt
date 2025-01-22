package com.example.primeraapp.ui.pantallaPostsOfUser

import com.example.primeraapp.domain.modelo.Post
import com.example.primeraapp.ui.common.UiEvent

data class PostUserState(
    val posts: List<Post> = emptyList(),
    val event: UiEvent? = null,
    val isLoading: Boolean = false
)