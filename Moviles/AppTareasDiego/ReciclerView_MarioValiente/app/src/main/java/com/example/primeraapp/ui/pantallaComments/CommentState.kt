package com.example.primeraapp.ui.pantallaComments

import com.example.primeraapp.domain.modelo.Comment
import com.example.primeraapp.ui.common.UiEvent

data class CommentState(
    val comments: List<Comment> = emptyList(),
    val event: UiEvent? = null,
    val isLoading: Boolean = false
)