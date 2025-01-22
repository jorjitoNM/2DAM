package com.example.primeraapp.ui.pantallaAdd

import com.example.primeraapp.domain.modelo.Post
import com.example.primeraapp.ui.common.UiEvent

data class AddState(
    val post: Post? = null,
    val event: UiEvent? = null,
    val isLoading: Boolean = false
)