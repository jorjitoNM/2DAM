package com.example.examen2evajorgenovillo.ui.login

import com.example.examen2evajorgenovillo.domain.model.User
import com.example.examen2evajorgenovillo.ui.common.UiEvent

data class LoginState (
    val user : User = User(),
    val isLoading : Boolean = false,
    val event : UiEvent? = null,
    val validated : Boolean = false,
)