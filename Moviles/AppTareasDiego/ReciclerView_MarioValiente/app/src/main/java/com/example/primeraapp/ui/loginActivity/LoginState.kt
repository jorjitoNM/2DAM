package com.example.primeraapp.ui.loginActivity

import com.example.primeraapp.ui.common.UiEvent

data class LoginState(
    val event: UiEvent? = null,
    val validado: Boolean = false,
    val userId: Int = 0
)
