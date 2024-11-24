package com.example.apptareas.ui.login

import com.example.apptareas.R
import com.example.apptareas.domain.model.User
import com.example.apptareas.ui.common.UiEvent

data class LogInState(
    val user: User = User(),
    val password : String = R.string.password.toString(),
    val logged : Boolean = false,
    val event: UiEvent? = null,
)