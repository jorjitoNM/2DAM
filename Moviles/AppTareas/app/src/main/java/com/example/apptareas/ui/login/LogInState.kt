package com.example.apptareas.ui.login

import com.example.apptareas.domain.model.User
import com.example.apptareas.ui.common.UiEvent
import com.example.apptareas.utilities.Constantes

data class LogInState(
    val user: User = User(),
    val password : String = Constantes.USER_ID,
    val logged : Boolean = false,
    val event: UiEvent? = null,
)