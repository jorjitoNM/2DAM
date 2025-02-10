package com.example.hospitalroomcompose.ui.login

import com.example.hospitalroomcompose.domain.model.User
import com.example.primeraapp.ui.common.UiEvent

data class LoginState (
    val user : User = User(),
    val isLoading : Boolean = false,
    val uiEvent : UiEvent? = null,
    val validated : Boolean = false,
)