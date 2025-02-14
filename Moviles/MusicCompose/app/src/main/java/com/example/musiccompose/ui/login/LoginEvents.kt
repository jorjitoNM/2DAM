package com.example.musiccompose.ui.login

import com.example.musiccompose.domain.model.User

sealed interface LoginEvents {
    data class Login (val user : User) : LoginEvents
    data class Register (val user : User) : LoginEvents
    data object EventDone : LoginEvents
    data class UpdateUsername(val username: String) : LoginEvents
    data class UpdatePassword(val password: String) : LoginEvents
}