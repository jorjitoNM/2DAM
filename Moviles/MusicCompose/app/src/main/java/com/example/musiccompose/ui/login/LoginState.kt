package com.example.musiccompose.ui.login

import com.example.musiccompose.domain.model.User
import com.example.musiccompose.ui.common.UiEvent

data class LoginState (
    val user : User = User(),
    val isLoading : Boolean = false,
    val uiEvent : UiEvent? = null,
    val validated : Boolean = false,
)