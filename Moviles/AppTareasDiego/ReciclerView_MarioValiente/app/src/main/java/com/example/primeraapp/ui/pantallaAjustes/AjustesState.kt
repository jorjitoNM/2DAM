package com.example.primeraapp.ui.pantallaAjustes

import com.example.primeraapp.domain.modelo.User
import com.example.primeraapp.ui.common.UiEvent

data class AjustesState(
    val user: User? = null,
    val event: UiEvent? = null,
    val isLoading: Boolean = false
)


