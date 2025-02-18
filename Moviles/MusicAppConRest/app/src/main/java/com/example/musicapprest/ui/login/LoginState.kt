package com.example.musicapprest.ui.login

import com.example.musicapprest.domain.model.User
import com.example.primeraapp.ui.common.UiEvent

data class LoginState (
    val user : User = User(),
    val isLoading : Boolean = false,
    val event : UiEvent? = null,
    val validated : Boolean = false,
)