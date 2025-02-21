package com.example.examen2evajorgenovillo.ui.login

import com.example.examen2evajorgenovillo.domain.model.User

sealed interface LoginEvents {
    data class Login (val user : User) : LoginEvents
    data object EventDone : LoginEvents
    data class UpdateUsername(val username: String) : LoginEvents
    data class UpdatePassword(val password: String) : LoginEvents
}