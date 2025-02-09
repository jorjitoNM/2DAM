package com.example.apptareascompose.ui.login

import com.example.apptareascompose.domain.model.User
import com.example.primeraapp.ui.common.UiEvent

data class LoginState (
    val user : User = User(),
    val isLoading : Boolean = false,
    val uiEvent : UiEvent? = null,
    val validated : Boolean = false,
)